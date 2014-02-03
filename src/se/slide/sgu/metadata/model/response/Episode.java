package se.slide.sgu.metadata.model.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="episode")
public class Episode {

    @XmlElement
    public String title;
    
    @XmlElement
    public String description;
    
    @XmlElement
    public String guid;
    
    @XmlElement
    public String transcript;
    
    @XmlElement
    public String image;
    
    @XmlElement
    public Quote quote;
    
    @XmlElement(name="sections")
    public Sections sections;
    
    @XmlElement
    public ScienceOrFiction scienceorfiction;
    
    public static class Sections {
        @XmlElement(name="section")
        public ArrayList<Section> listOfSection;    
    }
    
    public static class Quote {
        @XmlElement
        public String text;
        
        @XmlElement
        public String by;    
    }
    
}
