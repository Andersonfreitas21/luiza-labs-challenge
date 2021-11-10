package br.com.luizalabs.schedulerequest.api.controller.v1;

import br.com.luizalabs.schedulerequest.api.controller.ScheduleRequestController;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ScheduleRequestControllerV1")
@RequestMapping("api/v1")
@CrossOrigin("*")
public class ScheduleRequestControllerImpl implements ScheduleRequestController<SchedulingDTO, SchedulingFilter, SchedulingForm> {
}
