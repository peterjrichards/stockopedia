package net.thefuturetoday.stockopedia.securityanalytics;

import net.thefuturetoday.stockopedia.securityanalytics.dsl.Query;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

public class DslControllerUnitTest extends AbstractTest {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void singleLeftAttributeOperand_thenReturnsResult() throws Exception {
        Query query = queryBuilder
                .withSecurity("ABC")
                .withExpression(
                        expressionBuilder
                                .withOperation("*")
                                .withOperandA("sales")
                                .withOperandB(BigDecimal.valueOf(2))
                        .build()
                ).build();

        mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(mapToJson(query))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(8.0)));
    }

    @Test
    public void singleRightAttributeOperand_thenReturnsResult() throws Exception {
        Query query = queryBuilder
                .withSecurity("ABC")
                .withExpression(
                        expressionBuilder
                                .withOperation("*")
                                .withOperandA(BigDecimal.valueOf(2))
                                .withOperandB("sales")
                                .build()
                ).build();

        mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(mapToJson(query))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(8.0)));
    }

    @Test
    public void twoAttributeOperands_thenReturnsResult() throws Exception {
        Query query = queryBuilder
                .withSecurity("BCD")
                .withExpression(
                        expressionBuilder
                                .withOperation("/")
                                .withOperandA("price")
                                .withOperandB("eps")
                                .build()
                ).build();

        mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(mapToJson(query))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(0.5)));
    }

    @Test
    public void twoExpressionOperands_thenReturnsResult() throws Exception {
        Query query = queryBuilder
                .withSecurity("CDE")
                .withExpression(
                        expressionBuilder
                                .withOperation("-")
                                .withOperandA(
                                        expressionBuilder
                                        .withOperation("-")
                                        .withOperandA("eps")
                                        .withOperandB("shares")
                                        .build()
                                )
                                .withOperandB(
                                        expressionBuilder
                                        .withOperation("-")
                                        .withOperandA("assets")
                                        .withOperandB("liabilities")
                                        .build()
                                )
                                .build()
                ).build();

        mvc.perform(MockMvcRequestBuilders
                .post("/")
                .content(mapToJson(query))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))

                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(24.0)));
    }
}
