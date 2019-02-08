package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "EBX_REP_TYPE_STRUCTURE")
public class TypeStructure implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "code")
    String code;
    @Column(name = "libelle")
    String libelle;
}
