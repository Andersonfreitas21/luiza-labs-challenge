package br.com.luizalabs.schedulerequest.domain.data.v1.filter;

import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import br.com.luizalabs.schedulerequest.domain.data.enums.TypeToSend;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder(builderMethodName = "newBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingFilter {
    private Set<UUID> uuids;
    private LocalDateTime sendDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String messageContains;
    private StatusOfSchedule status;
    private TypeToSend type;
    private String addresseeLike;
    private String addresseeEquals;
}
