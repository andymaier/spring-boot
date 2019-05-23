package de.predic8.helden.monitoring;

import de.predic8.helden.repo.HeldRepository;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class StadtHealthIndicator extends AbstractHealthIndicator {

    private final HeldRepository heldRepository;

    public StadtHealthIndicator(HeldRepository heldRepository) {
        this.heldRepository = heldRepository;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (heldRepository.count() > 4) {
            builder.up();
            builder.status("Stadt ist sicher!");
        } else {
            builder.down();
            builder.status("Stadt in Gefahr!");
        }
        builder.withDetail("Anzahl Helden", heldRepository.count()).withDetail("Helden", heldRepository.findAllNames());
    }
}
