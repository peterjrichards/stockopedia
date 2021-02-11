package net.thefuturetoday.stockopedia.securityanalytics;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController implements ErrorController {
    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return  "Please refer to the author for information on how to use this application";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
