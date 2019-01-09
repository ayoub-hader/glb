package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Author SZaoui
 */

@Getter
@Setter
@Entity
@Table(name = "EBX_REP_STRUCTURE")
public class Structure implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    Long id;

    @Column(name="MATRICULE")
    String matricule;

    @Column(name="ACRONYME")
    String acronyme;

    @Column(name="NOM")
    String nom;

    @Column(name="TYPE_")
    String type;


}
