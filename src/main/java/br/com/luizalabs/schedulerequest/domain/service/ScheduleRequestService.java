package br.com.luizalabs.schedulerequest.domain.service;


import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;

public interface ScheduleRequestService<DTO, Form, Filter,Entity> {
    DTO create(Form form, TypeToSend toSend);
}
