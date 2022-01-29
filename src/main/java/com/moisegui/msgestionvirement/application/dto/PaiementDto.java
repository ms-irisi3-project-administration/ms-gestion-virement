package com.moisegui.msgestionvirement.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaiementDto {
    List<String> userMatricules;
    String ref;
    String libelle;
}
