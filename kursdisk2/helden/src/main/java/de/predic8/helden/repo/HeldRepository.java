package de.predic8.helden.repo;

import de.predic8.helden.domain.Held;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HeldRepository extends JpaRepository<Held, UUID> {
    List<Held> findByName(String name);

    List<Held> findByNameIgnoreCase(String name);

    List<Held> findByNameIgnoreCaseContaining(String name);

    @Query(value = "Select held.name from Held held")
    List<String> findAllNames();
}
