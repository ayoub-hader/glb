package fr.cfdt.gasel.groupeldap.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Author SZaoui
 */
@AllArgsConstructor
@Getter
public enum TypeStructureEnum {

    CONF("CONF"),
    SSR("SSR"),
    SSE("SSE"),
    DT("DT"),
    UIS("UIS"),
    UIT("UIT"),
    UL("UL"),
    UP("UP"),
    UT("UT"),
    UD("UD"),
    FD("FD"),
    URI("URI"),
    URR("URR"),
    SYND("SYND"),
    UFR("UFR"),
    UTR("UTR"),
    UCC("UCC"),
    UCR("UCR"),
    UFFA("UFFA"),
    SLF("SLF"),
    SLR("SLR"),
    SOF("SOF"),
    SOS("SOS");

    private String type;
}
