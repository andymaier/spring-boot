package de.predic8.helden.monitoring;

import de.predic8.helden.repo.HeldRepository;
import org.mockito.Mockito;
import org.springframework.boot.actuate.health.Health;

import java.util.Arrays;

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
}