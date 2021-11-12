package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class SchedulingDTO {
    private UUID uuid;
    private LocalDateTime sendDate;
    private AddresseeDTO addressee;
    private String message;
    private StatusOfSchedule status;
    private TypeToSend type;
}
