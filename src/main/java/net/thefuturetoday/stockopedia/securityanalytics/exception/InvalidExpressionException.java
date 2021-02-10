package net.thefuturetoday.stockopedia.securityanalytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException(String expressionString, Throwable innerException) {
        super(String.format("Invalid expression : %s", expressionString), innerException);
    }
}
