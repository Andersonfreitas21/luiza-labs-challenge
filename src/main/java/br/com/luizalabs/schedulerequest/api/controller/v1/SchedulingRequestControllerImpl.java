package br.com.luizalabs.schedulerequest.api.controller.v1;

import br.com.luizalabs.schedulerequest.api.controller.SchedulingRequestController;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.service.v1.ScheduleRequestServiceImpl;
import br.com.luizalabs.schedulerequest.exception.NotFoundException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
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
    @ApiOperation(httpMethod = "POST", value = "Create Scheduling Request", notes = "Create a scheduling request", response = SchedulingDTO.class)
    public ResponseEntity<SchedulingDTO> create(@ApiParam(name = "Scheduling", type = "Scheduling", value = "Scheduling Request", example = "Contact Number : (99)9999-9999", required = true)
                                                @Valid @RequestBody SchedulingForm schedulingForm,
                                                @ApiParam(name = "Type", type = "ENUM", value = "How to send a request", example = "WHATSAPP", required = true)
                                                @RequestParam(value = "Type") TypeToSend toSend) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleRequestService.create(schedulingForm, toSend));
    }

    @Override
    @GetMapping(value = "/schedule", produces = {"application/json; charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(httpMethod = "GET", value = "Get Scheduling Request for pageable", notes = "Get a scheduling request")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "page",
                    dataType = "integer",
                    paramType = "query",
                    value = "Results page you want to retrieve (0..N)",
                    defaultValue = "0"),
            @ApiImplicitParam(
                    name = "size",
                    dataType = "integer",
                    paramType = "query",
                    value = "Number of records per page",
                    defaultValue = "5"),
            @ApiImplicitParam(
                    name = "sort",
                    allowMultiple = true,
                    dataType = "string",
                    paramType = "query",
                    value = "Sort criteria in format: property(, asc | desc). The default sort order is ascending. Various sorting criteria are supported.")
    })
    public ResponseEntity<List<SchedulingDTO>> findByStatusSchedulingRequest(
            @ApiParam(name = "StatusOfSchedule", type = "ENUM", value = "Status of Schedule", example = "PENDING", required = true)
            @RequestParam(name = "StatusOfSchedule") StatusOfSchedule status,
            @ApiIgnore Pageable pageable) {

        Page<SchedulingDTO> schedulingDTOS = scheduleRequestService.find(status, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalElements", Long.toString(schedulingDTOS.getTotalElements()));
        headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "totalElements");

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(schedulingDTOS.getContent());
    }

    @Override
    @DeleteMapping(value = "/schedule/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(httpMethod = "DELETE", value = "Deletes a Scheduling Request by uuid", notes = "Delete a scheduling request")
    public ResponseEntity<?> delete(@PathVariable(name = "uuid") String uuid) throws NotFoundException {
        scheduleRequestService.delete(uuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    public List<SchedulingDTO> filter(SchedulingFilter schedulingFilter, Boolean isPaged, Integer page, Integer size, String[] properties, Sort.Direction direct, ServerHttpResponse response) {
        return null;
    }


}
