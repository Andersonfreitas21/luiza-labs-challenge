package br.com.luizalabs.schedulerequest.domain.data.v1.dto;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder(builderMethodName = "newBuilder")
@Getter
@Setter
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

}
