package de.predic8.helden.repo;

import de.predic8.helden.domain.Faehigkeit;
import de.predic8.helden.domain.Held;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//@ConditionalOnProperty(value = "spring.profiles.active", havingValue = "h2")
@Component
public class HeldenLieferservice implements ApplicationListener<ContextRefreshedEvent> {

    Logger log = LoggerFactory.getLogger(HeldenLieferservice.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        log.info("Running bootstrap code");
        FaehigkeitRepository faehigkeitRepo = applicationEvent.getApplicationContext().getBean(FaehigkeitRepository.class);
        Faehigkeit schrumpfen = faehigkeitRepo.save(new Faehigkeit("Schrumpfen"));
        Faehigkeit fliegen = faehigkeitRepo.save(new Faehigkeit("Fliegen"));
        Faehigkeit geschwindigkeit = faehigkeitRepo.save(new Faehigkeit("Schneller als der Blitz"));
        Faehigkeit extremeStaerke = faehigkeitRepo.save(new Faehigkeit("Extreme Staerke"));

        HeldRepository heldRepo = applicationEvent.getApplicationContext().getBean(HeldRepository.class);
        heldRepo.save(new Held("Antman", 70, schrumpfen));
        heldRepo.save(new Held("Karlsson vom Dach", 40, fliegen));
        heldRepo.save(new Held("Flash", 60, geschwindigkeit));
        heldRepo.save(new Held("Popeye", 80, extremeStaerke));
        heldRepo.save(new Held("Speedy Gonzales", 60, geschwindigkeit));
        log.info("Finished bootstrap code");
    }
}