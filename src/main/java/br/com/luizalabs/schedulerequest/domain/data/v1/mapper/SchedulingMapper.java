package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;

import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddresseeMapper.class})
public interface SchedulingMapper {

    SchedulingDTO toDTO(Scheduling entity);

    default Scheduling formToEntity(SchedulingForm form, TypeToSend type) {
        return null;
    }
}
