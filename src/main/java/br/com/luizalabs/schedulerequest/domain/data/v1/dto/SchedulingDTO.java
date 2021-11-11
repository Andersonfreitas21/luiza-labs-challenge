package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDTO {
    private LocalDateTime sendDate;
    private AddresseeDTO addressee;
    private String message;

}
