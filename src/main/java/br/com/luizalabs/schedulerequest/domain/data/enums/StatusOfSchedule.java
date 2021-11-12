package br.com.luizalabs.schedulerequest.domain.data.enums;

public enum StatusOfSchedule {
    PENDING("PENDING"), SENT("SENT"), DELETED("DELETED");

    private String status;

    StatusOfSchedule(String status) {
        this.status = status;
    }
}
