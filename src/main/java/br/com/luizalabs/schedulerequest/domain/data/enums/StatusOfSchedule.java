package br.com.luizalabs.schedulerequest.domain.data.enums;

import br.com.luizalabs.schedulerequest.exception.NotFoundException;

import java.util.Arrays;

public enum StatusOfSchedule {
    PENDING("PENDING"), SENT("SENT"), DELETED("DELETED");

    private String status;

    StatusOfSchedule(String status) {
        this.status = status;
    }

    private String getStatus() {
        return status;
    }

    public static StatusOfSchedule find(String status) throws NotFoundException {
        return Arrays.asList(StatusOfSchedule.values()).stream()
                .filter(s -> s.getStatus().equals(status.toUpperCase())).findFirst()
                .orElseThrow(() -> new NotFoundException(StatusOfSchedule.class, status));
    }
}
