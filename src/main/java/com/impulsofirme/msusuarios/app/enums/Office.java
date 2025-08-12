package com.impulsofirme.msusuarios.app.enums;

public enum Office {

    SAN_ANDRES("SAN ANDRES TUXTLA"),
    ACAYUCAN("ACAYUCAN"),
    ACAYUCAN_2("ACAYUCAN 2"),
    SISTEMAS("CENTRO OPERATIVO");

    private final String displayName;

    Office(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}