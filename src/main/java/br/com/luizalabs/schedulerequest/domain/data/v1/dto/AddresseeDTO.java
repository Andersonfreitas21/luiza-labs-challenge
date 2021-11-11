package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import lombok.*;

import java.util.List;

@Builder(builderMethodName = "newBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddresseeDTO {
    private String addressee;
    private String email;
    private String contactNumber;
    private List<SchedulingDTO> schedules;
}
