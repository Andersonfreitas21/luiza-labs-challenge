package br.com.luizalabs.schedulerequest.api.controller;

import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.exception.NotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;

import java.util.List;

public interface SchedulingRequestController<DTO, Filter, Form> {

    ResponseEntity<List<DTO>> findByStatusSchedulingRequest(StatusOfSchedule status, Pageable pageable);

    ResponseEntity<DTO> create(Form form, TypeToSend toSend);

    ResponseEntity delete(String uuid) throws NotFoundException;

    List<DTO> filter (Filter filter,
                      Boolean isPaged,
                      Integer page,
                      Integer size,
                      String[] properties,
                      Sort.Direction direct,
                      ServerHttpResponse response);

}
