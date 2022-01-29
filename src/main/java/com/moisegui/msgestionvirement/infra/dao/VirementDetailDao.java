package com.moisegui.msgestionvirement.infra.dao;

import com.moisegui.msgestionvirement.infra.entity.VirementDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirementDetailDao extends JpaRepository<VirementDetailEntity, Long> {
    public List<VirementDetailEntity> findByUserMatricule(String userMatricule);
    public List<VirementDetailEntity> findByEchelonRef(String echelonRef);
    public List<VirementDetailEntity> findByVirementRef(String virementRef);
}
