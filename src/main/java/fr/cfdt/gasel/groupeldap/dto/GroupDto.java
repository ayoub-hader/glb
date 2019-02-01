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
public class GroupDto {
    Long id;
    String name;
    String description;
    String membersNumber;
    RequestDto request;
    String structures;
    String denominationsResp;
    String denominationsMandat;
    String respInstances;
    String organismeInstances;
    String typesStructure;
}
