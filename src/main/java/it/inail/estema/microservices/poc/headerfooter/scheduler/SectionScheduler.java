
package it.inail.estema.microservices.poc.headerfooter.scheduler;

import it.inail.estema.microservices.poc.headerfooter.model.Section;
import it.inail.estema.microservices.poc.headerfooter.repositories.SectionRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SectionScheduler {
    
    private static final Logger log = LoggerFactory.getLogger(SectionScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static String schedulerUrlRecuperoHeader;
    private static String schedulerUrlRecuperoFooter;
    
    @Autowired
    private SectionRepository repository;
    
    @Scheduled(fixedRateString ="${scheduler.section.rate}", initialDelay=10000)
    public void recuperaSezioniDaRemoto() {

        log.debug("Recupero html header da {}", schedulerUrlRecuperoHeader);
        log.debug("Recupero html footer da {}", schedulerUrlRecuperoFooter);

        RestTemplate restTemp = new RestTemplate();
        
        if(schedulerUrlRecuperoHeader!=null && !"".equals(schedulerUrlRecuperoHeader)){
            String htmlHeader = restTemp.getForObject(schedulerUrlRecuperoHeader, String.class);
            log.debug("HTML HEADER-->{}", htmlHeader );
            updateToMongoDB("HEADER", htmlHeader); 
        }
        
        if(schedulerUrlRecuperoFooter!=null && !"".equals(schedulerUrlRecuperoFooter)){
            String htmlFooter = restTemp.getForObject(schedulerUrlRecuperoFooter, String.class);
            log.debug("HTML FOOTER-->{}", htmlFooter );
            updateToMongoDB("FOOTER", htmlFooter);
        }  
    }
    
    private void updateToMongoDB(String type, String html){
        try{
            Section section = repository.findSectionByType(type);
            
            if( section!=null ){
                section.setHtml(html);
                section.setUpdateDate(dateFormat.format(new Date()));
            }else{
                section = new Section();
                section.setHtml(html);
                section.setType(type);
                section.setUpdateDate(dateFormat.format(new Date()));
            }
            
            repository.save(section);
            log.info("Salvataggio su mongo db riuscito per " + type);
        
        }catch(Exception e){
            log.error("Errore salvataggio su mongo db:" + e.getLocalizedMessage());
        }
    }
    
    
    
    
    @Value("${scheduler.url.recupero.header}")
    public void setSchedulerUrlRecuperoHeader(String schedulerUrlRecuperoHeader) {
        SectionScheduler.schedulerUrlRecuperoHeader = schedulerUrlRecuperoHeader;
    }
    
    @Value("${scheduler.url.recupero.footer}")
    public void setSchedulerUrlRecuperoFooter(String schedulerUrlRecuperoFooter) {
        SectionScheduler.schedulerUrlRecuperoFooter = schedulerUrlRecuperoFooter;
    }
    
}
