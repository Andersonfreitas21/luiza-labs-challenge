//package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;
//
//import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
//import br.com.luizalabs.schedulerequest.domain.data.v1.dto.AddresseeDTO;
//import br.com.luizalabs.schedulerequest.domain.data.v1.form.AddresseeForm;
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Mapper(componentModel = "spring")
//public interface AddresseeMapper {
//
//    @Mapping(target = "addresseeName", source = "dto.addressee")
//    @Mapping(target = "email", source = "dto.email")
//    @Mapping(target = "contactNumber", source = "dto.contactNumber")
//    @Mapping(target = "schedules", source = "dto.schedules")
//    Addressee toEntity(AddresseeDTO dto);
//
//    List<Addressee> toEntity(List<AddresseeDTO> dto);
//
//    @InheritInverseConfiguration
//    AddresseeDTO toDTO(Addressee entity);
//
//    List<AddresseeDTO> toDTO(List<Addressee> entity);
//
//    default Addressee formToEntity(AddresseeForm form) {
//        return Addressee.newBuilder()
//                .addresseeName(form.getAddresseeName())
//                .contactNumber(form.getContactNumber())
//                .email(form.getEmail())
//                .schedules(new ArrayList<>())
//                .build();
//    }
//}
