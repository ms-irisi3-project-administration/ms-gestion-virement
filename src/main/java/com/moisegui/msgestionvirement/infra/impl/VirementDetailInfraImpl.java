package com.moisegui.msgestionvirement.infra.impl;

import com.moisegui.msgestionvirement.domain.pojo.VirementDetail;
import com.moisegui.msgestionvirement.infra.converter.VirementDetailConverter;
import com.moisegui.msgestionvirement.infra.core.AbstractInfraImpl;
import com.moisegui.msgestionvirement.infra.dao.VirementDetailDao;
import com.moisegui.msgestionvirement.infra.entity.VirementDetailEntity;
import com.moisegui.msgestionvirement.infra.facade.VirementDetailInfra;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VirementDetailInfraImpl extends AbstractInfraImpl implements VirementDetailInfra {
    private VirementDetailDao virementDetailDao;

    public VirementDetailInfraImpl(VirementDetailDao virementDetailDao) {
        this.virementDetailDao = virementDetailDao;
    }

    @Override
    public List<VirementDetailEntity> findByUserMatricule(String userMatricule) {
        return virementDetailDao.findByUserMatricule(userMatricule);
    }

    @Override
    public List<VirementDetailEntity> findByEchAndEchelonRef(String echelonRef) {
        return virementDetailDao.findByEchelonRef(echelonRef);
    }

    @Override
    public List<VirementDetailEntity> findByVirementRef(String virementRef) {
        return virementDetailDao.findByVirementRef(virementRef);
    }

    @Override
    public VirementDetail save(VirementDetail virementDetail) {
        if (virementDetail.getId() == null) {
            VirementDetailConverter.convertVirement = true;
            VirementDetailEntity virementDetailEntity = VirementDetailConverter.toEntity(virementDetail);
            VirementDetailConverter.convertVirement = true;
            VirementDetailConverter.convertVirement = true;
            VirementDetail virement = VirementDetailConverter.to(virementDetailDao.save(virementDetailEntity));
            VirementDetailConverter.convertVirement = false;
            return virement;
        }
        return null;
    }

    @Override
    public VirementDetail update(VirementDetail virementDetail) {
        if (virementDetail.getId() != null) {
            VirementDetailConverter.convertVirement = true;
            VirementDetailEntity virementDetailEntity = VirementDetailConverter.toEntity(virementDetail);
            VirementDetailConverter.convertVirement = true;
            return VirementDetailConverter.to(virementDetailDao.save(virementDetailEntity));
        }
        return null;
    }
}
