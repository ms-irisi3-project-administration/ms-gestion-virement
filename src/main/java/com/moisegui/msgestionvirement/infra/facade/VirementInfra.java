package com.moisegui.msgestionvirement.infra.facade;

import com.moisegui.msgestionvirement.domain.pojo.Virement;
import com.moisegui.msgestionvirement.infra.core.AbstractInfra;
import com.moisegui.msgestionvirement.infra.entity.VirementEntity;

import java.util.List;

public interface VirementInfra extends AbstractInfra {
    List<Virement> findAll();
    Virement findByRef(String ref);
    public List<VirementEntity> findByDateGreaterThanEqual(String date);
    public List<VirementEntity> findByDateLessThanEqual(String date);
    Virement save(Virement virement);
    Virement update(Virement virement);
    int deleteById(Long id);
    int deleteByRef(String ref);
}
