package br.com.luizalabs.schedulerequest.domain.data.enums;

import br.com.luizalabs.schedulerequest.exception.NotFoundException;

import java.util.Arrays;

public enum TypeToSend {

    WHATSAPP("WHATSAPP"), SMS("SMS"), EMAIL("EMAIL"), PUSH("PUSH");

    private String propertie;

    TypeToSend(String propertie) {
        this.propertie = propertie;
    }

    private String getPropertie() {
        return propertie;
    }

    public static TypeToSend find(String fromString) throws NotFoundException {
        return Arrays.asList(TypeToSend.values()).stream()
                .filter(type -> type.getPropertie().equals(fromString.toUpperCase())).findFirst()
                .orElseThrow(() -> new NotFoundException(TypeToSend.class, fromString));
    }
}
