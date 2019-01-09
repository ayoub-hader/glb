package fr.cfdt.gasel.groupeldap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Author SZaoui
 */
@NoArgsConstructor
@Getter
@Setter
public class ResponsabiliteInstanceDto implements Serializable {
    private Integer id;
    private String code;
    private String libelle;
    private String type;
}
