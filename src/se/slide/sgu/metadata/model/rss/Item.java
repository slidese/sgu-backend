package se.slide.sgu.metadata.model.rss;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class Item {

    @XmlElement
    public String title;
    
    @XmlElement
    public String description;
    
    @XmlElement
    public Enclosure enclosure;
    
    @XmlElement
    public String pubDate;
    
    @XmlElement
    public String guid;
    
    
    public static class Enclosure {
        
        @XmlAttribute
        public String url;
        
        @XmlAttribute
        public String length;
        
        @XmlAttribute
        public String type;
    }
}
