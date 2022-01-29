package com.moisegui.msgestionvirement.infra.impl;

import com.moisegui.msgestionvirement.domain.pojo.Virement;
import com.moisegui.msgestionvirement.infra.converter.VirementConverter;
import com.moisegui.msgestionvirement.infra.core.AbstractInfraImpl;
import com.moisegui.msgestionvirement.infra.dao.VirementDao;
import com.moisegui.msgestionvirement.infra.entity.VirementEntity;
import com.moisegui.msgestionvirement.infra.facade.VirementInfra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VirementInfraImpl extends AbstractInfraImpl implements VirementInfra {
    VirementDao virementDao;

    public VirementInfraImpl(VirementDao virementDao) {
        this.virementDao = virementDao;
    }

    @Override
    public List<Virement> findAll() {
        VirementConverter.convertVirementDetails = true;
        List<Virement> virements = VirementConverter.to(virementDao.findAll());
        VirementConverter.convertVirementDetails = false;
        return virements;
    }

    @Override
    public Virement findByRef(String ref) {
        VirementConverter.convertVirementDetails = true;
        Virement virement = VirementConverter.to(virementDao.findByRef(ref));
        VirementConverter.convertVirementDetails = false;
        return virement;
    }

    @Override
    public List<VirementEntity> findByDateGreaterThanEqual(String date) {
        return virementDao.findByDateGreaterThanEqual(date);
    }

    @Override
    public List<VirementEntity> findByDateLessThanEqual(String date) {
        return virementDao.findByDateLessThanEqual(date);
    }

    @Override
    public Virement save(Virement virement) {
        if(virement.getId() == null) {
            VirementConverter.convertVirementDetails = true;
            VirementEntity virementEntity = VirementConverter.toEntity(virement);
            VirementConverter.convertVirementDetails = true;
            Virement result = VirementConverter.to(virementDao.save(virementEntity));
            VirementConverter.convertVirementDetails = false;
            return result;
        }
        return null;
    }

    @Override
    public Virement update(Virement virement) {
        if(virement.getId() != null) {
            VirementConverter.convertVirementDetails = true;
            VirementEntity virementEntity = VirementConverter.toEntity(virement);
            VirementConverter.convertVirementDetails = true;
            Virement result = VirementConverter.to(virementDao.save(virementEntity));
            VirementConverter.convertVirementDetails = false;
            return result;
        }
        return null;
    }

    @Override
    public int deleteById(Long id) {
        virementDao.deleteById(id);
        return 1;
    }

    @Override
    public int deleteByRef(String ref) {
        if(ref != null) {
            Virement virement = findByRef(ref);
            if(virement != null) {
                virementDao.deleteById(virement.getId());
                return 1;
            }
        }
        return 0;
    }
}
