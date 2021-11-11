package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class AddresseeDTO {
    private String addressee;
    private String email;
    private String contactNumber;
    private List<SchedulingDTO> schedules;
}
