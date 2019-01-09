package fr.cfdt.gasel.groupeldap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StructureDto {
    Long id;
    String matricule;
    String acronyme;
    String nom;
    String type;
}
