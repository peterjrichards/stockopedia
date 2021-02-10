package net.thefuturetoday.stockopedia.securityanalytics.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.thefuturetoday.stockopedia.securityanalytics.data.FactRepository;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.Expression;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.Query;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.QueryResult;
import net.thefuturetoday.stockopedia.securityanalytics.exception.FactNotFoundException;
import net.thefuturetoday.stockopedia.securityanalytics.exception.InvalidExpressionException;
import net.thefuturetoday.stockopedia.securityanalytics.exception.UnsupportedOperatorException;
import net.thefuturetoday.stockopedia.securityanalytics.model.Fact;
import net.thefuturetoday.stockopedia.securityanalytics.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Controller
public class DslController {
    private final FactRepository factRepository;

    public DslController(FactRepository factRepository) {
        this.factRepository = factRepository;
    }

    @PostMapping("/")
    @ResponseBody
    public QueryResult evaluate(@RequestBody Query query) {
        Expression expression = query.getExpression();

        BigDecimal operandAValue = evaluateOperand(query.getSecurity(), expression.getOperandA());
        BigDecimal operandBValue = evaluateOperand(query.getSecurity(), expression.getOperandB());

        BigDecimal result = applyOperation(expression.getOperation(), operandAValue, operandBValue);
        return new QueryResult(query, result);
    }

    private BigDecimal evaluateOperand(String security, String operand) {
        if (StringUtils.isNumeric(operand)){
            return NumberUtils.createBigDecimal(operand);
        } else if (JsonUtils.isJson(operand)) {
            return evaluateExpression(security, operand);
        } else {
            return getFactValue(security, operand);
        }
    }

    private BigDecimal evaluateExpression(String security, String expressionString) {
        try {
            Expression expression = new ObjectMapper().readValue(expressionString, Expression.class);

            BigDecimal operandAValue = evaluateOperand(security, expression.getOperandA());
            BigDecimal operandBValue = evaluateOperand(security, expression.getOperandB());

            return applyOperation(expression.getOperation(), operandAValue, operandBValue);
        } catch (JsonProcessingException e) {
            throw new InvalidExpressionException(expressionString, e);
        }
    }

    private BigDecimal getFactValue(String security, String attribute) {
        Optional<Fact> optional = factRepository.getFact(security, attribute);
        Fact fact = optional.orElseThrow(() -> new FactNotFoundException(security, attribute));
        return fact.getValue();
    }

    private BigDecimal applyOperation(String operator, BigDecimal operandA, BigDecimal operandB) {
        if ("*".equals(operator)) {
            return operandA.multiply(operandB);
        } else if ("/".equals(operator)) {
            return operandA.divide(operandB, RoundingMode.HALF_DOWN);
        } else if ("+".equals(operator)) {
            return operandA.add(operandB);
        } else if ("-".equals(operator)) {
            return operandA.subtract(operandB);
        }
        throw new UnsupportedOperatorException(operator);
    }
}
