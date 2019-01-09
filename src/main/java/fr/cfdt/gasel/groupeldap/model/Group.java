package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Author SZaoui
 */
@Getter
@Setter
@Entity
@Table(name = "LDAP_GROUP")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_LDAP_GROUP")
    Long idGroup;

    @Column(name = "NAME")
    String name;

    @Column(name = "DESCRIPTION")
    String description;

    @Column(name = "MEMBERS_NUMBER")
    String membersNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_REQUEST")
    private Request request;
}

