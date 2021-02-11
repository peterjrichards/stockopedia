package net.thefuturetoday.stockopedia.securityanalytics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Collections;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DslControllerIntegrationTest {
    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private final HttpHeaders headers = new HttpHeaders();

    @Test
    public void singleLeftAttributeOperand_thenReturnsResult() throws Exception {
        final String expected = "{\"query\":{\"security\":\"ABC\",\"expression\":{\"fn\":\"*\",\"a\":\"sales\",\"b\":\"2\"}},\"result\":8.0}";
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        final QueryBuilder queryBuilder = new QueryBuilder();
        final ExpressionBuilder expressionBuilder = new ExpressionBuilder();

        final Query query = queryBuilder
                .withSecurity("ABC")
                .withExpression(
                        expressionBuilder
                                .withOperation("*")
                                .withOperandA("sales")
                                .withOperandB(BigDecimal.valueOf(2))
                                .build()
                ).build();

        final HttpEntity<String> entity = new HttpEntity<>(mapToJson(query), headers);

        final UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(createUrlWithPort());

        final ResponseEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createUrlWithPort() {
        return "http://localhost:" + port + "/";
    }

    private String mapToJson(final Object obj) throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
