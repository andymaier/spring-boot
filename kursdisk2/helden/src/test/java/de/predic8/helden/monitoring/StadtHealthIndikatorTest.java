package de.predic8.helden.monitoring;

import de.predic8.helden.repo.HeldRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.actuate.health.Health;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StadtHealthIndikatorTest {

    StadtHealthIndicator indicator;
    Health.Builder builder;

    private HeldRepository initMockRepo(long numHeros) {
        HeldRepository repo = Mockito.mock(HeldRepository.class);
        Mockito.when(repo.count()).thenReturn(numHeros);
        if(numHeros == 4)
            Mockito.when(repo.findAllNames()).thenReturn(Arrays.asList("Till", "Thomas", "Tobias", "Tim"));
        else if(numHeros == 5)
            Mockito.when(repo.findAllNames()).thenReturn(Arrays.asList("Till", "Thomas", "Tobias", "Tim", "Thorsten"));
        else
            throw new IllegalArgumentException("Bisher nur für 4 und 5 implementiert");
        return repo;
    }

    public void init(long numHeros){
        indicator = new StadtHealthIndicator(initMockRepo(numHeros));
        builder = new Health.Builder();
    }

    @Test
    public void testHealthUpWith5Heros() throws Exception {
        init(5);
        indicator.doHealthCheck(builder);
        assertEquals("Stadt ist sicher!", builder.build().getStatus().toString());
        assertEquals(5L, builder.build().getDetails().get("Anzahl Helden"));
        Arrays.asList("Till", "Thomas", "Tobias", "Tim", "Thorsten").stream()
                .forEach(expected -> assertTrue(((List)builder.build().getDetails().get("Helden")).contains(expected)));
    }

    @Test
    public void testHealthDownWith4Heros() throws Exception {
        init(4);
        indicator.doHealthCheck(builder);
        assertEquals("Stadt in Gefahr!", builder.build().getStatus().toString());
        assertEquals(4L, builder.build().getDetails().get("Anzahl Helden"));
        Arrays.asList("Till", "Thomas", "Tobias", "Tim").stream().forEach(expected -> assertTrue(((List)builder.build().getDetails().get("Helden")).contains(expected)));
    }

    @Test
    public void testStadtInsecureWith4Heros() throws Exception {
        init(4);
        indicator.doHealthCheck(builder);
        assertNotEquals("Stadt ist sicher!", builder.build().getStatus().toString());
    }
}
