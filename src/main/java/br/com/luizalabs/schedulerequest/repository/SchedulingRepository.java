package br.com.luizalabs.schedulerequest.repository;

import br.com.luizalabs.schedulerequest.domain.data.entity.Scheduling;
import br.com.luizalabs.schedulerequest.domain.data.enums.StatusOfSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {
    Page<Scheduling> findSchedulingByStatus(StatusOfSchedule status, Pageable pageable);
}
