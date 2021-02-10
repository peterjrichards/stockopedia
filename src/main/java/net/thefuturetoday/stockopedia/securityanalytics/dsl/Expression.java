package net.thefuturetoday.stockopedia.securityanalytics.dsl;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Expression {
    @JsonProperty("fn")
    private String operation;

    @JsonProperty("a")
    @JsonDeserialize(using = OperandDeserializer.class)
    private String operandA;

    @JsonProperty("b")
    @JsonDeserialize(using = OperandDeserializer.class)
    private String operandB;

    public Expression() {
    }

    public Expression(String operation, String operandA, String operandB) {
        setOperation(operation);
        setOperandA(operandA);
        setOperandB(operandB);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperandA() {
        return operandA;
    }

    public void setOperandA(String operandA) {
        this.operandA = operandA;
    }

    public String getOperandB() {
        return operandB;
    }

    public void setOperandB(String operandB) {
        this.operandB = operandB;
    }
}
