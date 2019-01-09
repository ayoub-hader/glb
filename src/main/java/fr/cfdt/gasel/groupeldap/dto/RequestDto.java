package fr.cfdt.gasel.groupeldap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Author Zaoui Soukaina
 */
@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    Long idRequest;

    String request;

    Date creationDate;

    Date lastExecutionDate;

    String executor;
}
