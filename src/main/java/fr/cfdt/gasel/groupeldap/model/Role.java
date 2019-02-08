package fr.cfdt.gasel.groupeldap.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "EBX_REP_ROLE")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "code")
    String code;
    @Column(name = "libelle")
    String libelle;
    @Column(name = "actif")
    Boolean actif;
}
