package br.com.luizalabs.schedulerequest.repository;

import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {
}
