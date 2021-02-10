package net.thefuturetoday.stockopedia.securityanalytics.api;

import net.thefuturetoday.stockopedia.securityanalytics.data.AttributeRepository;
import net.thefuturetoday.stockopedia.securityanalytics.model.Attribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttributeController {
    private final AttributeRepository repository;

    public AttributeController(AttributeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/attributes")
    @ResponseBody
    public List<Attribute> all() {
        return repository.findAll();
    }
}
