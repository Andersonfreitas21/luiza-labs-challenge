package br.com.luizalabs.schedulerequest.repository;

import br.com.luizalabs.schedulerequest.domain.data.entity.Addressee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddresseeRepository extends JpaRepository<Addressee, UUID> {
}
