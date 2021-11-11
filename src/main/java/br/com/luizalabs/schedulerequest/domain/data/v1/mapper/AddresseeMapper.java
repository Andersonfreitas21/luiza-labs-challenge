package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import br.com.luizalabs.schedulerequest.domain.data.v1.dto.AddresseeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SchedulingMapper.class})
public interface AddresseeMapper {

//    Addressee toEntity(AddresseeDTO dto);
//
//    AddresseeDTO toDTO(Addressee entity);

}
