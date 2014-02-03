package se.slide.sgu.metadata.model.rss;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rss")
public class RSS {

    @XmlElement
    public Channel channel;
    
    public static class Channel {
        
        @XmlElement(name = "item")
        public ArrayList<Item> listOfItem;
    }
}
