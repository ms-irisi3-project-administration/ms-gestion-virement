package com.moisegui.msgestionvirement.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VirementDetail {
    Long id;

    String userMatricule;
    BigDecimal montant;
    String echelonRef;

    private List<String> primeRefs;

    private Virement virement;

    public VirementDetail(String userMatricule, BigDecimal montant, String echelonRef, List<String> primeRefs) {
        this.userMatricule = userMatricule;
        this.montant = montant;
        this.echelonRef = echelonRef;
        this.primeRefs = primeRefs;
    }
}
