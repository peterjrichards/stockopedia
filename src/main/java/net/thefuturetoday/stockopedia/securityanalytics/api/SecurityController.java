package net.thefuturetoday.stockopedia.securityanalytics.api;

import net.thefuturetoday.stockopedia.securityanalytics.data.SecurityRepository;
import net.thefuturetoday.stockopedia.securityanalytics.model.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SecurityController {
    private final SecurityRepository repository;

    public SecurityController(SecurityRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/securities")
    @ResponseBody
    public List<Security> all() {
        return repository.findAll();
    }
}
