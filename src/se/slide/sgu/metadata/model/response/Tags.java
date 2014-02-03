package se.slide.sgu.metadata.model.response;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;

public class Tags {
    @XmlElement
    public ArrayList<String> tag;
}
