package fr.cfdt.gasel.groupeldap.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author Zaoui Soukaina
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * Success response code.
     */
    SUCCESS(200) ,
    /**
     * Error response code.
     */
    ERROR(500) ,
    /**
     * Warning response code.
     */
    WARNING(400) ;

    private Integer code;

}
