package fr.cfdt.gasel.groupeldap.model;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Author Zaoui Soukaina
 */
@Getter
@Setter
@Entity
@Table(name = "LDAP_GROUP_REQUEST")
public class Request {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_REQUEST")
    Long idRequest;

    @Column(name = "REQUEST")
    String request;

    @Column(name = "CREATION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @ApiParam("test")
    Date creationDate;

    @Column(name = "LAST_EXECUTION_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    Date lastExecutionDate;

    @Column(name = "EXECUTOR")
    String executor;
}
