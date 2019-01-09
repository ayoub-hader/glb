package fr.cfdt.gasel.groupeldap.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author SZaoui
 */
@AllArgsConstructor
@Getter
public enum BranchEnum {

    GASEL("gasel"),
    VALIDATION("validation"),
    QUARANTAINE("quarantaine"),
    TEMPORAIRE("temporaire");

    private String code;
}
