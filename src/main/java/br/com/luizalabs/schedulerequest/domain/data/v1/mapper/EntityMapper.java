package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.AddresseeDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;

import java.util.List;

public interface EntityMapper {

    SchedulingDTO toDto(Scheduling scheduling);

    List<SchedulingDTO> toDto(List<Scheduling> scheduling);

    AddresseeDTO toDtoAdd(Addressee addressee);

    List<AddresseeDTO> toDtoAdd(List<Addressee> addressee);

    Addressee formToEntity(AddresseeForm addresseeForm);

    Scheduling formToEntity(SchedulingForm schedulingForm, Addressee addressee, TypeToSend type);
}
