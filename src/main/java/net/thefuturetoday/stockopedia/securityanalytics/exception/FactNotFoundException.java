package net.thefuturetoday.stockopedia.securityanalytics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FactNotFoundException extends RuntimeException {
    public FactNotFoundException(String securitySymbol, String attributeName) {
        super(String.format("Not found attribute %s for %s", attributeName, securitySymbol));
    }
}
