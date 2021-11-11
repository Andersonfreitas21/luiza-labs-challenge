package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "newBuilder")
public class SchedulingDTO {
    private LocalDateTime sendDate;
    private Addressee addressee;
    private String message;
}
