package com.moisegui.msgestionvirement.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VirementDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String userMatricule;
    BigDecimal montant;
    String echelonRef;

    @ElementCollection(targetClass = String.class)
    private List<String> primeRefs;

    @ManyToOne
    private VirementEntity virement;
}
