package fr.cfdt.gasel.groupeldap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author SZaoui
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleDto {
    Integer id;
    String code;
    String libelle;
    Boolean actif;
}
