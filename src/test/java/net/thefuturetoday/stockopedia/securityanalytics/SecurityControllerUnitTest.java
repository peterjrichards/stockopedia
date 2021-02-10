package net.thefuturetoday.stockopedia.securityanalytics;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class SecurityControllerUnitTest extends AbstractTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void securities_thenReturnsAllSecurities() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/securities")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", Matchers.hasSize(10)));
    }
}
