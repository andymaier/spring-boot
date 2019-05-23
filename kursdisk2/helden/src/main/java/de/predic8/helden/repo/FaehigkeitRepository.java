package de.predic8.helden.repo;

import de.predic8.helden.domain.Faehigkeit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FaehigkeitRepository extends JpaRepository<Faehigkeit, UUID> {
}
