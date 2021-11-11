package br.com.luizalabs.schedulerequest.api.controller.v1;

import br.com.luizalabs.schedulerequest.api.controller.SchedulingRequestController;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.SchedulingMapper;
import br.com.luizalabs.schedulerequest.domain.service.ScheduleRequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("ScheduleRequestControllerV1")
@RequestMapping("api/v1")
@CrossOrigin("*")
public class SchedulingRequestControllerImpl implements SchedulingRequestController<SchedulingDTO, SchedulingFilter, SchedulingForm> {

    private final ScheduleRequestService scheduleRequestService;
    private final SchedulingMapper schedulingMapper;

    public SchedulingRequestControllerImpl(ScheduleRequestService scheduleRequestService, SchedulingMapper schedulingMapper) {
        this.scheduleRequestService = scheduleRequestService;
        this.schedulingMapper = schedulingMapper;
    }

    @Override
    @PostMapping("/schedule")
    public SchedulingDTO create(@RequestBody SchedulingForm schedulingForm, @RequestParam(value = "TypeToSend") TypeToSend toSend) {
        return null;
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
