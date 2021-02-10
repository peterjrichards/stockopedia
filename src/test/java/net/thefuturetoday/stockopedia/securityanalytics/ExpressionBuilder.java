package net.thefuturetoday.stockopedia.securityanalytics;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.Expression;

import java.math.BigDecimal;

public class ExpressionBuilder {
    private String operation;
    private String operandA;
    private String operandB;

    public ExpressionBuilder withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public ExpressionBuilder withOperandA(BigDecimal value) {
        this.operandA = value.toPlainString();
        return this;
    }

    public ExpressionBuilder withOperandA(String attributeName) {
        this.operandA = attributeName;
        return this;
    }

    public ExpressionBuilder withOperandA(Expression expression) throws JsonProcessingException {
        this.operandA = new ObjectMapper().writeValueAsString(expression);
        return this;
    }

    public ExpressionBuilder withOperandB(BigDecimal value) {
        this.operandB = value.toPlainString();
        return this;
    }

    public ExpressionBuilder withOperandB(String attributeName) {
        this.operandB = attributeName;
        return this;
    }

    public ExpressionBuilder withOperandB(Expression expression) throws JsonProcessingException {
        this.operandB = new ObjectMapper().writeValueAsString(expression);
        return this;
    }

    public Expression build() {
        return new Expression(operation, operandA, operandB);
    }
}
