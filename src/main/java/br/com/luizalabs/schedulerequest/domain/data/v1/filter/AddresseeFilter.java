package br.com.luizalabs.schedulerequest.domain.data.v1.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder(builderMethodName = "newBuilder")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddresseeFilter {
    private Set<UUID> uuids;
    private String addresseeLike;
    private String addresseeEquals;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<UUID> schedulesUuid;
}
