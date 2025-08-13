package com.impulsofirme.msusuarios.app.enums;

public enum Office {

    SAN_ANDRES_TUXTLA("SAN ANDRÉS TUXTLA"),
    ACAYUCAN("ACAYUCAN"),
    ACAYUCAN_2("ACAYUCAN 2"),
    CENTRO_OPERACIONES("CENTRO OPERACIONES");

    private final String displayName;

    Office(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}