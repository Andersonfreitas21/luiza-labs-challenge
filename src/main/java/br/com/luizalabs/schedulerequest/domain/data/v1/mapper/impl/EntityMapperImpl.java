package br.com.luizalabs.schedulerequest.domain.data.v1.mapper.impl;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.AddresseeDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.mapper.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EntityMapperImpl implements EntityMapper {

    @Override
    public SchedulingDTO toDto(Scheduling scheduling) {
        return SchedulingDTO.builder()
                .uuid(scheduling.getUuid())
                .sendDate(scheduling.getSendDate())
                .message(scheduling.getMessage())
                .addressee(toDtoAdd(scheduling.getAddressee()))
                .build();
    }

    @Override
    public List<SchedulingDTO> toDto(List<Scheduling> scheduling) {
        List<SchedulingDTO> schedulingDTOS = new ArrayList<>();
        scheduling.forEach(scheduling1 -> {
            schedulingDTOS.add(toDto(scheduling1));
        });
        return schedulingDTOS;
    }

    @Override
    public AddresseeDTO toDtoAdd(Addressee addressee) {
        return AddresseeDTO.builder()
                .addressee(addressee.getAddressee())
                .contactNumber(addressee.getContactNumber())
                .email(addressee.getEmail())
                .build();
    }

    @Override
    public List<AddresseeDTO> toDtoAdd(List<Addressee> addressee) {
        List<AddresseeDTO> addresseeDTOS = new ArrayList<>();
        addressee.forEach(addressee1 -> {
            addresseeDTOS.add(toDtoAdd(addressee1));
        });
        return addresseeDTOS;
    }

    @Override
    public Addressee formToEntity(AddresseeForm addresseeForm) {
        return Addressee.builder()
                .addressee(addresseeForm.getAddresseeName())
                .contactNumber(addresseeForm.getContactNumber())
                .email(addresseeForm.getEmail())
                .build();
    }

    @Override
    public Scheduling formToEntity(SchedulingForm schedulingForm, Addressee addressee, TypeToSend type) {
        return Scheduling.builder()
                .addressee(addressee)
                .status(StatusOfSchedule.PENDING)
                .message(schedulingForm.getMessage())
                .sendDate(schedulingForm.getSendDate())
                .type(type)
                .build();
    }
}
