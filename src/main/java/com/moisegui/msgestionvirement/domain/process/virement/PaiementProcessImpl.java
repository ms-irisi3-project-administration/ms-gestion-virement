package com.moisegui.msgestionvirement.domain.process.virement;

import com.moisegui.msgestionvirement.application.dto.MailParameter;
import com.moisegui.msgestionvirement.domain.core.AbstractProcessImpl;
import com.moisegui.msgestionvirement.domain.core.Result;
import com.moisegui.msgestionvirement.domain.pojo.Virement;
import com.moisegui.msgestionvirement.domain.pojo.VirementDetail;
import com.moisegui.msgestionvirement.infra.facade.VirementDetailInfra;
import com.moisegui.msgestionvirement.infra.facade.VirementInfra;
import com.moisegui.msgestionvirement.infra.impl.MailSender;
import com.moisegui.msgestionvirement.infra.proxy.GestionPaieService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaiementProcessImpl extends AbstractProcessImpl<PayerProcessInput> implements PaiementProcess {
    private GestionPaieService gestionPaieService;
    private VirementInfra virementInfra;
    private VirementDetailInfra virementDetailInfra;
    private MailSender mailSender;

    public PaiementProcessImpl(GestionPaieService gestionPaieService, VirementInfra virementInfra, VirementDetailInfra virementDetailInfra, MailSender mailSender) {
        this.gestionPaieService = gestionPaieService;
        this.virementInfra = virementInfra;
        this.virementDetailInfra = virementDetailInfra;
        this.mailSender = mailSender;
    }

    @Override
    public void validate(PayerProcessInput abstractProcessInput, Result result) {

        if(abstractProcessInput.getRef() == null || abstractProcessInput.getRef().isEmpty()){
            result.addErrorMessage(virementInfra.getMessage("payer.error.ref.required"));
        }
        else {
            if(virementInfra.findByRef(abstractProcessInput.getRef()) != null){
                result.addErrorMessage(virementInfra.getMessage("payer.error.ref.already.exist"));
            }
        }

        if(abstractProcessInput.getLibelle() == null || abstractProcessInput.getLibelle().isEmpty()){
            result.addErrorMessage(virementInfra.getMessage("payer.error.libelle.required"));
        }

        List<String> userMatricules = abstractProcessInput.getUserMatricules();

        if(userMatricules == null || userMatricules.isEmpty()){
            result.addErrorMessage(virementInfra.getMessage("payer.error.user.required"));
        }

        // here we should see if a user does not exit
    }

    @Override
    public void run(PayerProcessInput abstractProcessInput, Result result) {
        List<String> userMatricules = abstractProcessInput.getUserMatricules();

        List<VirementDetail> virementDetails = new ArrayList<>();
        for(String userMatricule : userMatricules){
            VirementDetail virementDetail = getVirementDetailsForUser(userMatricule, result);
            if(virementDetail != null){
                virementDetails.add(virementDetail);
            }
        }

        if(!virementDetails.isEmpty()){
            BigDecimal montantTotal = BigDecimal.ZERO;
            for(VirementDetail virementDetail : virementDetails){
                montantTotal = montantTotal.add(virementDetail.getMontant());
            }
            Virement virement = virementInfra.save(new Virement(abstractProcessInput.getRef(), abstractProcessInput.getLibelle(),new Date(), montantTotal));
            for(VirementDetail virementDetail : virementDetails){
                virementDetail.setVirement(virement);
                virementDetailInfra.save(virementDetail);
                // send Email to the user
                mailSender.sendKafkaMessage(new MailParameter("bestofgui@gmail.com", "Nouveau virement pour " + virementDetail.getUserMatricule(), " Vous venez de recevoir un virement de " + virementDetail.getMontant() + " MAD"));
            }

            result.addInfoMessage(virementInfra.getMessage("payer.success.message"));
            result.setOutput(virementInfra.findByRef(virement.getRef()));
        }
    }

    private VirementDetail getVirementDetailsForUser(String userMatricule, Result result){
        // get user current echelon
        BigDecimal salaireDeBase = gestionPaieService.findUserCurrentEchelonMontant(userMatricule);

        // get user current primes
        BigDecimal primesMontant = gestionPaieService.findUserCurrentPrimesMontant(userMatricule);

        if(salaireDeBase != null){
            String echelonRef = gestionPaieService.findActiveEchelonForUser(userMatricule);
            List<String> activePrimes = gestionPaieService.findActivePrimesForUser(userMatricule);
            return new VirementDetail(userMatricule, salaireDeBase, echelonRef, activePrimes);
        }

        result.addWarningMessage(virementInfra.getMessage("payer.warning.user.echelon.not.found")+ " " + userMatricule);
        return null;
    }
}
