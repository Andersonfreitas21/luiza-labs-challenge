//package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;
//
//import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
//import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
//import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
//import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
//import br.com.luizalabs.schedulerequest.domain.data.v1.dto.SchedulingDTO;
//import br.com.luizalabs.schedulerequest.domain.data.v1.form.SchedulingForm;
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface SchedulingMapper {
//
//    @Mapping(target = "sendDate", source = "dto.sendDate")
//    @Mapping(target = "addressee", source = "dto.addressee")
//    @Mapping(target = "message", source = "dto.message")
//    Scheduling toEntity(SchedulingDTO dto);
//
//    List<Scheduling> toEntity(List<SchedulingDTO> dto);
//
//    @InheritInverseConfiguration
//    SchedulingDTO toDTO(Scheduling entity);
//
//    List<SchedulingDTO> toDTO(List<Scheduling> entity);
//
//    default Scheduling formToEntity(SchedulingForm form, Addressee addressee, TypeToSend type) {
//        return Scheduling.newBuilder()
//                .sendDate(form.getSendDate())
//                .message(form.getMessage())
//                .status(StatusOfSchedule.PENDING)
//                .type(type)
//                .addressee(addressee)
//                .build();
//    }
//}
