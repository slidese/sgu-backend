package se.slide.sgu.metadata.model.fetch;

import se.slide.sgu.metadata.model.response.Episode;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="fetch")
public class FetchResponse {
    
    @XmlElement
    public String status;
    
    @XmlElement
    public String message;

    @XmlElement
    public Episode episode;
    
}
