package com.moisegui.msgestionvirement.infra.facade;

import com.moisegui.msgestionvirement.domain.pojo.VirementDetail;
import com.moisegui.msgestionvirement.infra.core.AbstractInfra;
import com.moisegui.msgestionvirement.infra.entity.VirementDetailEntity;

import java.util.List;

public interface VirementDetailInfra extends AbstractInfra {
    public List<VirementDetailEntity> findByUserMatricule(String userMatricule);
    public java.util.List<VirementDetailEntity> findByEchAndEchelonRef(String echelonRef);
    public List<VirementDetailEntity> findByVirementRef(String virementRef);
    public VirementDetail save(VirementDetail virementDetail);
    public VirementDetail update(VirementDetail virementDetail);
}
