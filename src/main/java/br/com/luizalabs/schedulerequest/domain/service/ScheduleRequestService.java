package br.com.luizalabs.schedulerequest.domain.service;


import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleRequestService<DTO, Form, Filter,Entity> {
    DTO create(Form form, TypeToSend toSend);
    Page<DTO> find(StatusOfSchedule status, Pageable pageable);

    void delete(String uuid) throws NotFoundException;
}
