package se.slide.sgu.metadata.model.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="section")
public class Section {

    @XmlElement
    public String number;
    
    @XmlElement
    public String title;
    
    @XmlElement
    public String start;
    
    @XmlElement
    public ArrayList<String> link;
    
    @XmlElement
    public Tags tags;
    
}
