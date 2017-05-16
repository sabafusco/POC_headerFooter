package it.inail.estema.microservices.poc.headerfooter.service.controller;

import it.inail.estema.microservices.poc.headerfooter.model.Section;
import it.inail.estema.microservices.poc.headerfooter.repositories.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceControllerSection {
    
    @Autowired
    private SectionRepository repository;

    @RequestMapping("/section/{type}")
    @ResponseBody
    public String getSection(@PathVariable String type) {
        Section section;
        try {
            section = repository.findSectionByType(type.toUpperCase());
            return section.getHtml();
        } catch (Exception e) {
            return "Sezione non trovata";
        }
    }
    
}
