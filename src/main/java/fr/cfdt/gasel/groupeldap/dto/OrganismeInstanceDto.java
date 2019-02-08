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
public class OrganismeInstanceDto {
    private Integer id;
    private String code;
    private String libelle;
    private String type;
}
