package com.moisegui.msgestionvirement.infra.converter;

import com.moisegui.msgestionvirement.domain.pojo.Virement;
import com.moisegui.msgestionvirement.infra.entity.VirementEntity;

import java.util.ArrayList;
import java.util.List;

public class VirementConverter {
    public static boolean convertVirementDetails;

    public static Virement to(VirementEntity virementEntity) {
        if (virementEntity == null) {
            return null;
        }

        Virement virement = new Virement();
        virement.setId(virementEntity.getId());
        virement.setRef(virementEntity.getRef());
        virement.setLibelle(virementEntity.getLibelle());
        virement.setDate(virementEntity.getDate());
        virement.setMontant(virementEntity.getMontant());

        if (convertVirementDetails) {
            virement.setVirementDetails(VirementDetailConverter.to(virementEntity.getVirementDetails()));
        }

        return virement;
    }

    public static VirementEntity toEntity(Virement virement) {
        if (virement == null) {
            return null;
        }

        VirementEntity virementEntity = new VirementEntity();
        virementEntity.setId(virement.getId());
        virementEntity.setRef(virement.getRef());
        virementEntity.setLibelle(virement.getLibelle());
        virementEntity.setDate(virement.getDate());
        virementEntity.setMontant(virement.getMontant());

        if (convertVirementDetails) {
            virementEntity.setVirementDetails(VirementDetailConverter.toEntity(virement.getVirementDetails()));
        }

        return virementEntity;
    }

    public static List<Virement> to(List<VirementEntity> virementEntities) {
        if (virementEntities == null) {
            return null;
        }

        List<Virement> virements = new ArrayList<Virement>();
        for (VirementEntity virementEntity : virementEntities) {
            virements.add(to(virementEntity));
        }

        convertVirementDetails = false;

        return virements;
    }

    public static List<VirementEntity> toEntity(List<Virement> virements) {
        if (virements == null) {
            return null;
        }

        List<VirementEntity> virementEntities = new ArrayList<VirementEntity>();
        for (Virement virement : virements) {
            virementEntities.add(toEntity(virement));
        }

        convertVirementDetails = false;

        return virementEntities;
    }
}
