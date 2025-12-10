package com.smartcity.orchestrator.DTOs;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


public enum TypeFacture {
    ELECT("ELECT"),

    EAU("EAU"),

    PARKING("PARKING");

    private final String value;

    TypeFacture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeFacture fromValue(String value) {
        for (TypeFacture type : TypeFacture.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Type de facture invalide: " + value);
    }
}
