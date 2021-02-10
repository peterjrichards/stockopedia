package net.thefuturetoday.stockopedia.securityanalytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_IMPLEMENTED)
public class UnsupportedOperatorException extends RuntimeException {
    public UnsupportedOperatorException(String operator) {
        super(String.format("The %s operation is not supported", operator));
    }
}
