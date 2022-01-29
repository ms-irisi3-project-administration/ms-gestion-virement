package com.moisegui.msgestionvirement.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class VirementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String ref;
    String libelle;
    Date date;
    BigDecimal montant;

    @OneToMany(mappedBy = "virement", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("virement")
    List<VirementDetailEntity> virementDetails;
}
