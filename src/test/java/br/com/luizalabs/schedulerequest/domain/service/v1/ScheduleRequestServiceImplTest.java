package br.com.luizalabs.schedulerequest.domain.service.v1;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.EntityMapper;
import br.com.luizalabs.schedulerequest.exception.NotFoundException;
import br.com.luizalabs.schedulerequest.repository.AddresseeRepository;
import br.com.luizalabs.schedulerequest.repository.SchedulingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScheduleRequestServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScheduleRequestServiceImplTest {
    @MockBean
    private AddresseeRepository addresseeRepository;

    @MockBean
    private EntityMapper entityMapper;

    @Autowired
    private ScheduleRequestServiceImpl scheduleRequestServiceImpl;

    @MockBean
    private SchedulingRepository schedulingRepository;

    @Test
    void testCreate() {
        Addressee addressee = new Addressee();
        addressee.setEmail("andersonfreitas21@gmail.com");
        addressee.setSchedules(new ArrayList<Scheduling>());
        addressee.setContactNumber("(88)997128191");
        addressee.setCreatedAt(LocalDateTime.now());
        addressee.setUpdatedAt(LocalDateTime.now());
        addressee.setAddressee("Anderson Freitas");
        addressee.setUuid(UUID.randomUUID());

        Scheduling scheduling = new Scheduling();
        scheduling.setMessage("Not all who wander are lost");
        scheduling.setType(TypeToSend.WHATSAPP);
        scheduling.setAddressee(addressee);
        scheduling.setStatus(StatusOfSchedule.PENDING);
        scheduling.setCreatedAt(LocalDateTime.now());
        scheduling.setSendDate(LocalDateTime.now());
        scheduling.setUpdatedAt(LocalDateTime.now());
        scheduling.setUuid(UUID.randomUUID());
        when(this.schedulingRepository.save((Scheduling) any())).thenReturn(scheduling);

        Addressee addressee1 = new Addressee();
        addressee1.setEmail("andersonfreitas21@gmail.com");
        addressee1.setSchedules(new ArrayList<Scheduling>());
        addressee1.setContactNumber("(88)997128191");
        addressee1.setCreatedAt(LocalDateTime.now());
        addressee1.setUpdatedAt(LocalDateTime.now());
        addressee1.setAddressee("Anderson Freitas");
        addressee1.setUuid(UUID.randomUUID());

        Scheduling scheduling1 = new Scheduling();
        scheduling1.setMessage("Not all who wander are lost");
        scheduling1.setType(TypeToSend.WHATSAPP);
        scheduling1.setAddressee(addressee1);
        scheduling1.setStatus(StatusOfSchedule.PENDING);
        scheduling1.setCreatedAt(LocalDateTime.now());
        scheduling1.setSendDate(LocalDateTime.now());
        scheduling1.setUpdatedAt(LocalDateTime.now());
        scheduling1.setUuid(UUID.randomUUID());
        SchedulingDTO schedulingDTO = new SchedulingDTO();
        when(this.entityMapper.toDto((Scheduling) any())).thenReturn(schedulingDTO);
        when(this.entityMapper.formToEntity((SchedulingForm) any(), (Addressee) any(), (TypeToSend) any()))
                .thenReturn(scheduling1);

        Addressee addressee2 = new Addressee();
        addressee2.setEmail("andersonfreitas21@gmail.com");
        addressee2.setSchedules(new ArrayList<Scheduling>());
        addressee2.setContactNumber("(88)997128191");
        addressee2.setCreatedAt(LocalDateTime.now());
        addressee2.setUpdatedAt(LocalDateTime.now());
        addressee2.setAddressee("Anderson Freitas");
        addressee2.setUuid(UUID.randomUUID());
        Optional<Addressee> ofResult = Optional.<Addressee>of(addressee2);
        when(this.addresseeRepository.findAddresseeByAddresseeAndContactNumberAndEmail((String) any(), (String) any(),
                (String) any())).thenReturn(ofResult);

        SchedulingForm schedulingForm = new SchedulingForm();
        schedulingForm.setAddressee(new AddresseeForm("Anderson Freitas", "andersonfreitas21@gmail.com", "(88)997128191"));
        assertSame(schedulingDTO, this.scheduleRequestServiceImpl.create(schedulingForm, TypeToSend.WHATSAPP));
        verify(this.schedulingRepository).save((Scheduling) any());
        verify(this.entityMapper).formToEntity((SchedulingForm) any(), (Addressee) any(), (TypeToSend) any());
        verify(this.entityMapper).toDto((Scheduling) any());
        verify(this.addresseeRepository).findAddresseeByAddresseeAndContactNumberAndEmail((String) any(), (String) any(),
                (String) any());
    }

    @Test
    void testFind() {
        when(this.schedulingRepository.findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<Scheduling>(new ArrayList<Scheduling>()));
        when(this.entityMapper.toDto((List<Scheduling>) any())).thenReturn(new ArrayList<SchedulingDTO>());
        assertTrue(this.scheduleRequestServiceImpl.find(StatusOfSchedule.PENDING, null).toList().isEmpty());
        verify(this.schedulingRepository).findSchedulingByStatus((StatusOfSchedule) any(),
                (org.springframework.data.domain.Pageable) any());
        verify(this.entityMapper).toDto((List<Scheduling>) any());
    }

    @Test
    void testDelete() throws NotFoundException {
        Addressee addressee = new Addressee();
        addressee.setEmail("andersonfreitas21@gmail.com");
        addressee.setSchedules(new ArrayList<Scheduling>());
        addressee.setContactNumber("(88)997128191");
        addressee.setCreatedAt(LocalDateTime.now());
        addressee.setUpdatedAt(LocalDateTime.now());
        addressee.setAddressee("Anderson Freitas");
        addressee.setUuid(UUID.randomUUID());

        Scheduling scheduling = new Scheduling();
        scheduling.setMessage("Not all who wander are lost");
        scheduling.setType(TypeToSend.WHATSAPP);
        scheduling.setAddressee(addressee);
        scheduling.setStatus(StatusOfSchedule.PENDING);
        scheduling.setCreatedAt(LocalDateTime.now());
        scheduling.setSendDate(LocalDateTime.now());
        scheduling.setUpdatedAt(LocalDateTime.now());
        scheduling.setUuid(UUID.randomUUID());
        Optional<Scheduling> ofResult = Optional.<Scheduling>of(scheduling);

        Addressee addressee1 = new Addressee();
        addressee1.setEmail("andersonfreitas21@gmail.com");
        addressee1.setSchedules(new ArrayList<Scheduling>());
        addressee1.setContactNumber("(88)997128191");
        addressee1.setCreatedAt(LocalDateTime.now());
        addressee1.setUpdatedAt(LocalDateTime.now());
        addressee1.setAddressee("Anderson Freitas");
        addressee1.setUuid(UUID.randomUUID());

        Scheduling scheduling1 = new Scheduling();
        scheduling1.setMessage("Not all who wander are lost");
        scheduling1.setType(TypeToSend.WHATSAPP);
        scheduling1.setAddressee(addressee1);
        scheduling1.setStatus(StatusOfSchedule.PENDING);
        scheduling1.setCreatedAt(LocalDateTime.now());
        scheduling1.setSendDate(LocalDateTime.now());
        scheduling1.setUpdatedAt(LocalDateTime.now());
        scheduling1.setUuid(UUID.randomUUID());
        when(this.schedulingRepository.saveAndFlush((Scheduling) any())).thenReturn(scheduling1);
        when(this.schedulingRepository.findById((UUID) any())).thenReturn(ofResult);
        this.scheduleRequestServiceImpl.delete("01234567-89AB-CDEF-FEDC-BA9876543210");
        verify(this.schedulingRepository).findById((UUID) any());
        verify(this.schedulingRepository).saveAndFlush((Scheduling) any());
    }
}

