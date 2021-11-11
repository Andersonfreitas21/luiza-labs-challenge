package br.com.luizalabs.schedulerequest.api.controller;

import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.server.ServerHttpResponse;

import java.util.List;

public interface SchedulingRequestController<DTO, Filter, Form> {

    Page<DTO> findByStatusSchedulingRequest(String status, Pageable pageable);

    List<DTO> filter (Filter filter,
                      Boolean isPaged,
                      Integer page,
                      Integer size,
                      String[] properties,
                      Sort.Direction direct,
                      ServerHttpResponse response);

    DTO create(Form form, TypeToSend toSend);

}
