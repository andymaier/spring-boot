package de.predic8.helden;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.predic8.helden.domain.Held;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiMockMvcTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testModifyHeld() throws Exception {
        mockMvc.perform(get("/helden/actions/search?name=antman")).andDo(result -> {
            Held antman = mapResult(result)[0];
            antman.setName("Elephantman");
            mockMvc.perform(put("/helden/" + antman.getId()).contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(antman)));
            mockMvc.perform(get("/helden/" + antman.getId())).andExpect(content().string(containsString("Elephantman")));
        }).andExpect(status().isOk());
    }

    private Held[] mapResult(MvcResult heldenResult) throws Exception {
        return new ObjectMapper().readValue(heldenResult.getResponse().getContentAsString(),Held[].class);
    }
}
