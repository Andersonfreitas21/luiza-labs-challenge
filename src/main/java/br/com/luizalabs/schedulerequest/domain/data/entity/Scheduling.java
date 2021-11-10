package br.com.luizalabs.schedulerequest.domain.data.entity;

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
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

}
