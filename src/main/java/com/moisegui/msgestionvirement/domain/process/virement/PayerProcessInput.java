package com.moisegui.msgestionvirement.domain.process.virement;

import com.moisegui.msgestionvirement.domain.core.AbstractProcessInput;
import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class PayerProcessInput extends AbstractProcessInput {
    String ref;
    String libelle;
    List<String> userMatricules;

    public PayerProcessInput(String ref, String libelle, List<String> userMatricules) {
        this.ref = ref;
        this.libelle = libelle;
        this.userMatricules = userMatricules;
    }
}
