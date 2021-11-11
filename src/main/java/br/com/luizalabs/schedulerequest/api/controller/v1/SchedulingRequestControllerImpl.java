package br.com.luizalabs.schedulerequest.api.controller.v1;

import br.com.luizalabs.schedulerequest.api.controller.SchedulingRequestController;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.service.v1.ScheduleRequestServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ScheduleRequestControllerV1")
@RequestMapping("api/v1")
@CrossOrigin("*")

public class SchedulingRequestControllerImpl implements SchedulingRequestController<SchedulingDTO, SchedulingFilter, SchedulingForm> {

    private final ScheduleRequestServiceImpl scheduleRequestService;

    public SchedulingRequestControllerImpl(ScheduleRequestServiceImpl scheduleRequestService) {
        this.scheduleRequestService = scheduleRequestService;
    }

    @Override
    @PostMapping(value = "/schedule", produces = {"application/json; charset=UTF-8"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create Scheduling Request", notes = "Create a scheduling request")
    public SchedulingDTO create(@ApiParam(name = "Scheduling", type = "Scheduling", value = "Scheduling Request", example = "Contact Number : (99)9999-9999", required = true)
                                @RequestBody SchedulingForm schedulingForm,
                                @ApiParam(name = "Type", type = "ENUM", value = "How to send a request", example = "WHATSAPP", required = true)
                                @RequestParam(value = "Type") TypeToSend toSend) {
        return scheduleRequestService.create(schedulingForm, toSend);
    }

    @Override
    @GetMapping("/schedule/{status}")
    public Page<SchedulingDTO> findByStatusSchedulingRequest(@PathVariable(name = "status") String status, Pageable pageable) {
        return null;
    }

    @Override
    public List<SchedulingDTO> filter(SchedulingFilter schedulingFilter, Boolean isPaged, Integer page, Integer size, String[] properties, Sort.Direction direct, ServerHttpResponse response) {
        return null;
    }


}
