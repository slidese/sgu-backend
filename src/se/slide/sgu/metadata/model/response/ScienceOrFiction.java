package se.slide.sgu.metadata.model.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class ScienceOrFiction {
    
    @XmlElement(name="item")
    public ArrayList<Item> listOfItem;

    public static class Item {
        @XmlElement
        public String title;
        
        @XmlElement
        public String description;
        
        @XmlElement
        public ArrayList<String> link;
        
        @XmlElement
        public Tags tags;
        
    }
}
