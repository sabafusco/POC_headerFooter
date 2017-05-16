package it.inail.estema.microservices.poc.headerfooter.model;

import org.springframework.data.annotation.Id;

public class Section {
    
    @Id
    private String id;
    private String updateDate;
    private String type;
    private String html;

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, type='%s', html='%s', updateDate='%s']",
                id, type, html,updateDate );
    }

    
    public String getUpdateDate() {
        return updateDate;
    }

    public String getType() {
        return type;
    }

    public String getHtml() {
        return html;
    }


    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
}
