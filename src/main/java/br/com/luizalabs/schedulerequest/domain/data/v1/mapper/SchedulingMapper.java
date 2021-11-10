package br.com.luizalabs.schedulerequest.domain.data.v1.mapper;

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
public class SchedulingMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

}
