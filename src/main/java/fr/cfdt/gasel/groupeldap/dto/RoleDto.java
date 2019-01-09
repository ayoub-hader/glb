package fr.cfdt.gasel.groupeldap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author SZaoui
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleDto implements Serializable {
    Integer id;
    String code;
    String libelle;
    Boolean actif;
}
