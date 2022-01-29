package com.moisegui.msgestionvirement.infra.dao;

import com.moisegui.msgestionvirement.infra.entity.VirementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirementDao extends JpaRepository<VirementEntity, Long> {
    public VirementEntity findByRef(String ref);
    public List<VirementEntity> findByDateGreaterThanEqual(String date);
    public List<VirementEntity> findByDateLessThanEqual(String date);
}
