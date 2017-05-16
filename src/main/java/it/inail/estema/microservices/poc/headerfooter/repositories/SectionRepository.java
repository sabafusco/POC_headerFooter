package it.inail.estema.microservices.poc.headerfooter.repositories;

import it.inail.estema.microservices.poc.headerfooter.model.Section;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SectionRepository extends MongoRepository<Section, Long> {

    Section findSectionByType(String type);

}
