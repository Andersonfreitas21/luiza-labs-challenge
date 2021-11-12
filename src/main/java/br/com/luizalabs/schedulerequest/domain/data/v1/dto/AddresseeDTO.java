package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import lombok.*;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class AddresseeDTO {
    private String addressee;
    private String email;
    private String contactNumber;
}
