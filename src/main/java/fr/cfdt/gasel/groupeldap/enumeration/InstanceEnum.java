package fr.cfdt.gasel.groupeldap.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author SZaoui
 */
@AllArgsConstructor
@Getter
public enum InstanceEnum {
    STRUCTURE("structure"),
    PERSONNE("personne"),
    PARAMETRE("parametres"),
    ADMINISTRATION("administration"),
    VALIDATION("validation");

    private String code;
}
