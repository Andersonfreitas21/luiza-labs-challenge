package br.com.luizalabs.schedulerequest.domain.service.v1;

import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.SchedulingMapper;
import br.com.luizalabs.schedulerequest.domain.service.ScheduleRequestService;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleRequestServiceImpl implements ScheduleRequestService<SchedulingDTO, SchedulingForm, SchedulingFilter, Scheduling> {

    private final SchedulingRepository schedulignRequestRepository;
    private final SchedulingMapper schedulingMapper;

    public ScheduleRequestServiceImpl(SchedulingRepository schedulignRequestRepository, SchedulingMapper schedulingMapper) {
        this.schedulignRequestRepository = schedulignRequestRepository;
        this.schedulingMapper = schedulingMapper;
    }

    @Override
    public Scheduling create(SchedulingForm schedulingForm, TypeToSend type) {
        return schedulignRequestRepository.save(schedulingMapper.formToEntity(schedulingForm, type));
    }
}
