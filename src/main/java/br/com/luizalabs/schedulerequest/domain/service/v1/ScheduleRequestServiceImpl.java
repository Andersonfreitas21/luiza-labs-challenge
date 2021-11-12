package br.com.luizalabs.schedulerequest.domain.service.v1;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.EntityMapper;
import br.com.luizalabs.schedulerequest.domain.service.ScheduleRequestService;
import br.com.luizalabs.schedulerequest.exception.NotFoundException;
import br.com.luizalabs.schedulerequest.repository.AddresseeRepository;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ScheduleRequestServiceImpl implements ScheduleRequestService<SchedulingDTO, SchedulingForm, SchedulingFilter, Scheduling> {

    private final SchedulingRepository schedulignRequestRepository;
    private final AddresseeRepository addresseeRepository;
    private final EntityMapper entityMapper;

    public ScheduleRequestServiceImpl(SchedulingRepository schedulignRequestRepository, AddresseeRepository addresseeRepository, EntityMapper entityMapper) {
        this.schedulignRequestRepository = schedulignRequestRepository;
        this.addresseeRepository = addresseeRepository;
        this.entityMapper = entityMapper;
    }

    @Override
    @Transactional
    public SchedulingDTO create(SchedulingForm schedulingForm, TypeToSend type) {
        AtomicReference<SchedulingDTO> schedulingDTO = new AtomicReference<>(SchedulingDTO.builder().build());

        // Checking if there is already a recipient with request data.
        Optional<Addressee> addressee = addresseeRepository.findAddresseeByAddresseeAndContactNumberAndEmail(schedulingForm.getAddressee().getAddresseeName(), schedulingForm.getAddressee().getContactNumber(), schedulingForm.getAddressee().getEmail());

        if (addressee.isPresent()) {
            schedulingDTO.set(entityMapper.toDto(
                    schedulignRequestRepository.save(
                            entityMapper.formToEntity(schedulingForm, addressee.get(), type))));
        } else {
            Addressee addressee1 = addresseeRepository.save(entityMapper.formToEntity(schedulingForm.getAddressee()));
            schedulingDTO.set(entityMapper.toDto(
                    schedulignRequestRepository.save(
                            entityMapper.formToEntity(schedulingForm, addressee1, type))));
        }

        return schedulingDTO.get();
    }

    @Override
    public Page<SchedulingDTO> find(StatusOfSchedule status, Pageable pageable) {
        return new PageImpl<>(entityMapper.toDto(schedulignRequestRepository.findSchedulingByStatus(status, pageable).getContent()));
    }

    @Override
    public void delete(String uuid) throws NotFoundException {

        Optional<Scheduling> scheduling = schedulignRequestRepository.findById(UUID.fromString(uuid));

        if (scheduling.isPresent()) {
            schedulignRequestRepository.delete(scheduling.get());
        } else {
            throw new NotFoundException(Scheduling.class, "Schedule notfound");
        }
    }

}
