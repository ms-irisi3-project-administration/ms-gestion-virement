package com.moisegui.msgestionvirement.application.util;

import com.moisegui.msgestionvirement.domain.process.virement.PaiementProcess;
import com.moisegui.msgestionvirement.domain.process.virement.PaiementProcessImpl;
import com.moisegui.msgestionvirement.infra.facade.VirementDetailInfra;
import com.moisegui.msgestionvirement.infra.facade.VirementInfra;
import com.moisegui.msgestionvirement.infra.impl.MailSender;
import com.moisegui.msgestionvirement.infra.proxy.GestionPaieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Processinjection {
    @Bean
    PaiementProcess paiementProcess(GestionPaieService gestionPaieService, VirementInfra virementInfra, VirementDetailInfra virementDetailInfra, MailSender mailSender) {
        return new PaiementProcessImpl(gestionPaieService, virementInfra, virementDetailInfra, mailSender);
    }
}

