package de.predic8.helden.repo;

import de.predic8.helden.domain.Held;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeldRepository  extends JpaRepository<Held, UUID> {

  List<Held> findByName(String name);

}
