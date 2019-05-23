package de.predic8.helden;

import de.predic8.helden.domain.Held;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testModifyHeld() {
        Held antman = restTemplate.getForEntity("/helden/actions/search?name=antman", Held[].class).getBody()[0];
        antman.setName("Elephantman");

        restTemplate.put("/helden/" + antman.getId(),antman);

        assertEquals("Elephantman", restTemplate.getForEntity("/helden/" + antman.getId(), Held.class).getBody().getName());
    }
}
