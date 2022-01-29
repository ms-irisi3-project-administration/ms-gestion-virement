package com.moisegui.msgestionvirement.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Virement {
    Long id;
    String ref;
    String libelle;
    Date date;
    BigDecimal montant;
    List<VirementDetail> virementDetails;

    public Virement(String ref, String libelle, Date date, BigDecimal montant) {
        this.ref = ref;
        this.libelle = libelle;
        this.date = date;
        this.montant = montant;
    }
}
