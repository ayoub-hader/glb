package fr.cfdt.gasel.groupeldap.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Author SZaoui
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel("get all personnes")
public class PersonneDto{

    @ApiModelProperty("person id")
    Long id;

    @ApiModelProperty("person last name")
    String nom;

    @ApiModelProperty("person synd")
    StructureDto syndicat;

    @ApiModelProperty("person grade")
    String npa;

    @ApiModelProperty("person birth name")
    String nomNaissance;

    @ApiModelProperty("person firstName")
    String prenom;
}
