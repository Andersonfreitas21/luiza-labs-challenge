package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

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
}
