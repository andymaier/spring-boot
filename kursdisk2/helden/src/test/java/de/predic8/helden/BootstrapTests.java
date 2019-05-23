package de.predic8.helden;

import de.predic8.helden.repo.FaehigkeitRepository;
import de.predic8.helden.repo.HeldRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTests {

    @Autowired
    FaehigkeitRepository faehigkeitRepository;

    @Autowired
    HeldRepository heldRepository;

    @Test
    public void testDatabaseIsFilled(){
        assertEquals(5, faehigkeitRepository.count());
        assertEquals(5, heldRepository.count());
    }
}
