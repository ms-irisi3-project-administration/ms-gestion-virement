package com.moisegui.msgestionvirement.infra.converter;

import com.moisegui.msgestionvirement.domain.pojo.VirementDetail;
import com.moisegui.msgestionvirement.infra.entity.VirementDetailEntity;

import java.util.ArrayList;
import java.util.List;

public class VirementDetailConverter {
    public static boolean convertVirement;

    public static VirementDetail to(VirementDetailEntity virementDetailEntity) {
        if (virementDetailEntity == null) {
            return null;
        }

        VirementDetail virementDetail = new VirementDetail();
        virementDetail.setId(virementDetailEntity.getId());
        virementDetail.setUserMatricule(virementDetailEntity.getUserMatricule());
        virementDetail.setMontant(virementDetailEntity.getMontant());
        virementDetail.setEchelonRef(virementDetailEntity.getEchelonRef());
        virementDetail.setPrimeRefs(virementDetailEntity.getPrimeRefs());

        if (convertVirement) {
            virementDetail.setVirement(VirementConverter.to(virementDetailEntity.getVirement()));
        }

        return virementDetail;
    }

    public static VirementDetailEntity toEntity(VirementDetail virementDetail) {
        if (virementDetail == null) {
            return null;
        }

        VirementDetailEntity virementDetailEntity = new VirementDetailEntity();
        virementDetailEntity.setId(virementDetail.getId());
        virementDetailEntity.setUserMatricule(virementDetail.getUserMatricule());
        virementDetailEntity.setMontant(virementDetail.getMontant());
        virementDetailEntity.setEchelonRef(virementDetail.getEchelonRef());
        virementDetailEntity.setPrimeRefs(virementDetail.getPrimeRefs());

        if (convertVirement) {
            virementDetailEntity.setVirement(VirementConverter.toEntity(virementDetail.getVirement()));
        }

        return virementDetailEntity;
    }

    public static List<VirementDetail> to(List<VirementDetailEntity> virementDetailEntities) {
        if (virementDetailEntities == null) {
            return null;
        }

        List<VirementDetail> virementDetails = new ArrayList<>();
        for (VirementDetailEntity virementDetailEntity : virementDetailEntities) {
            virementDetails.add(to(virementDetailEntity));
        }

        convertVirement = false;

        return virementDetails;
    }

    public static List<VirementDetailEntity> toEntity(List<VirementDetail> virementDetails) {
        if (virementDetails == null) {
            return null;
        }

        List<VirementDetailEntity> virementDetailEntities = new ArrayList<>();
        for (VirementDetail virementDetail : virementDetails) {
            virementDetailEntities.add(toEntity(virementDetail));
        }

        convertVirement = false;

        return virementDetailEntities;
    }

}
