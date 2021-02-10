package net.thefuturetoday.stockopedia.securityanalytics.api;


import net.thefuturetoday.stockopedia.securityanalytics.data.FactRepository;
import net.thefuturetoday.stockopedia.securityanalytics.model.Fact;
import net.thefuturetoday.stockopedia.securityanalytics.model.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class FactController {
    private final FactRepository repository;

    public FactController(FactRepository repository) { this.repository = repository; }

    @GetMapping("/facts")
    @ResponseBody
    public List<Fact> all() {
        return repository.findAll();
    }
}
