package br.com.luizalabs.schedulerequest.domain.service.v1;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.filter.SchedulingFilter;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.service.ScheduleRequestService;
import br.com.luizalabs.schedulerequest.repository.AddresseeRepository;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ScheduleRequestServiceImpl implements ScheduleRequestService<SchedulingDTO, SchedulingForm, SchedulingFilter, Scheduling> {

    private final SchedulingRepository schedulignRequestRepository;
    private final AddresseeRepository addresseeRepository;

    public ScheduleRequestServiceImpl(SchedulingRepository schedulignRequestRepository, AddresseeRepository addresseeRepository) {
        this.schedulignRequestRepository = schedulignRequestRepository;
        this.addresseeRepository = addresseeRepository;
    }

    @Override
    public SchedulingDTO create(SchedulingForm schedulingForm, TypeToSend type) {

        Addressee addressee = addresseeRepository.save(formToEntity(schedulingForm.getAddressee()));

        return toDTO(schedulignRequestRepository.save(formToEntity(schedulingForm, addressee, type)));
    }

    protected final Addressee formToEntity(AddresseeForm addressee) {
        return Addressee.newBuilder()
                .addressee(addressee.getAddresseeName())
                .email(addressee.getEmail())
                .contactNumber(addressee.getContactNumber())
                .schedules(new ArrayList<>())
                .build();
    }

    protected final Scheduling formToEntity(SchedulingForm schedulingForm, Addressee addressee,TypeToSend type ) {
        return Scheduling.newBuilder()
                .addressee(addressee)
                .status(StatusOfSchedule.PENDING)
                .message(schedulingForm.getMessage())
                .sendDate(schedulingForm.getSendDate())
                .type(type)
                .build();
    }

    protected final SchedulingDTO toDTO(Scheduling scheduling){
        return SchedulingDTO.newBuilder()
                .addressee(scheduling.getAddressee())
                .message(scheduling.getMessage())
                .sendDate(scheduling.getSendDate())
                .build();
    }
}
