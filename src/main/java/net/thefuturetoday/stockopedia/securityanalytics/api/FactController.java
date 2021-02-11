package net.thefuturetoday.stockopedia.securityanalytics.api;


import net.thefuturetoday.stockopedia.securityanalytics.data.FactRepository;
import net.thefuturetoday.stockopedia.securityanalytics.model.Fact;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FactController {
    private final FactRepository repository;

    public FactController(FactRepository repository) { this.repository = repository; }

    @GetMapping("/facts")
    public List<Fact> all() {
        return repository.findAll();
    }
}
