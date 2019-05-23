package de.predic8.helden.repo;

import de.predic8.helden.domain.Faehigkeit;
import de.predic8.helden.domain.Held;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaehigkeitRepository extends JpaRepository<Faehigkeit, UUID> {

  //https://www.dropbox.com/s/7djca2hcjwn2kpe/kursdisk2.zip?dl=0

}
