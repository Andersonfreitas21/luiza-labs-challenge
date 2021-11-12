package br.com.luizalabs.schedulerequest.domain.data.enums;

public enum TypeToSend {

    WHATSAPP("WHATSAPP"), SMS("SMS"), EMAIL("EMAIL"), PUSH("PUSH");

    private String propertie;

    TypeToSend(String propertie) {
        this.propertie = propertie;
    }

}
