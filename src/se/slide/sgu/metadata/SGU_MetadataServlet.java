
package se.slide.sgu.metadata;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import se.slide.sgu.metadata.model.fetch.FetchResponse;
import se.slide.sgu.metadata.model.response.Episode;
import se.slide.sgu.metadata.model.response.Episode.Quote;
import se.slide.sgu.metadata.model.response.ScienceOrFiction;
import se.slide.sgu.metadata.model.response.ScienceOrFiction.Item;
import se.slide.sgu.metadata.model.response.Section;
import se.slide.sgu.metadata.model.rss.RSS;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@SuppressWarnings("serial")
public class SGU_MetadataServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/xml");

        String inputPreId = req.getParameter("inputPreId");

        // Our response for this fetch request
        FetchResponse fetch = new FetchResponse();
        fetch.episode = new Episode();
        fetch.episode.sections = new Episode.Sections();
        fetch.episode.quote = new Episode.Quote();

        // Different parts of our fetch response
        RSS rss = null;
        ArrayList<Section> listOfSection = new ArrayList<Section>();

        try {
            URL url = new URL("http://www.theskepticsguide.org/feed/sgu");
            rss = (RSS) JAXBContext.newInstance(RSS.class).createUnmarshaller().unmarshal(url.openStream());

        } catch (JAXBException e) {
            e.printStackTrace();
            printError(resp, "500", "Error loading RSS info");
            return;
        }

        if (rss == null || rss.channel == null || rss.channel.listOfItem == null) {
            // Make checks
        }
        
        for (se.slide.sgu.metadata.model.rss.Item item : rss.channel.listOfItem) {
            if (item.title.contains("The Skeptics Guide #" + inputPreId)) {
                fetch.episode.title = item.title;
                fetch.episode.description = item.description;
                fetch.episode.guid = item.guid;
                fetch.episode.transcript = "http://www.sgutranscripts.org/wiki/SGU_Episode_" + inputPreId;
            }
        }

        try {
            Document doc = Jsoup.connect("http://www.theskepticsguide.org/podcast/sgu/" + inputPreId).get();
            
            // Image
            Element podcastImage = doc.select("div.podcast-image img").first();
            fetch.episode.image = findImageName(podcastImage);
            
            // Sections
            Elements segments = doc.select("div.podcast-segment");
            for (Element tag : segments) {
                Elements headers = tag.select("h3");
                
                for (Element header : headers) {
                    String title = header.text();
                    
                    if (title.contains("This Day in Skepticism")) {
                        Section section = handleThisDayInSkepticism(tag);
                        listOfSection.add(section);
                    }
                    else if (title.contains("Who's That Noisy")) {
                        Section section = new Section();
                        section.title = "Who's That Noisy";
                        listOfSection.add(section);
                    }
                    else if (title.contains("Science or Fiction")) {
                        Section section = new Section();
                        section.title = "Science or Fiction";
                        listOfSection.add(section);
                        
                        fetch.episode.scienceorfiction = parseScienceOrFiction(tag);
                    }
                    else if (title.contains("Skeptical Quote of the Week")) {
                        Quote quote = parseQuote(tag);
                        fetch.episode.quote = quote;
                    }
                    else {
                        Section section = handleGenericSection(tag);
                        listOfSection.add(section);
                    }
                    
                    //System.out.println(header.text());
                }
                
                //System.out.println(tag.tagName());
            }
            
            fetch.episode.sections.listOfSection = listOfSection;
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JAXBContext context = JAXBContext.newInstance(FetchResponse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            fetch.status = "200";
            fetch.message = "Request successful";

            m.marshal(fetch, resp.getOutputStream());

        } catch (JAXBException e) {
            e.printStackTrace();
            printError(resp, "501", "Error parsing RSS info");
            return;
            // resp.setContentType("text/plain");
            // resp.getWriter().println("Hello, world " + inputPreId +
            // builder.toString());
        }

    }
    
    private String findImageName(Element img) {
        Element src = img.select("[src]").first();
        String filename = src.text().substring(src.text().lastIndexOf("/") + 1);
        return filename;
    }
    
    private String findTitle(String text) {
        int index = text.indexOf("http://");
        
        if (index < 0)
            return text;
        
        return text.substring(0, index);
    }
    
    private Section handleThisDayInSkepticism(Element header) {
        Element label = header.select("span.podcast-item-label").first();
        Element value = header.select("span.podcast-item-value").first();
        
        ArrayList<String> listOfUrls = findUrls(value.text());
        
        Section section = new Section();
        section.title = "This Day in Skepticism, " + label.text() + ": " + findTitle(value.text()).trim();
        section.link = listOfUrls;
        
        return section;
    }
    
    private Section handleGenericSection(Element header) {
        Element label = header.select("span.podcast-item-label").first();
        Element value = header.select("span.podcast-item-value").first();
        
        ArrayList<String> listOfUrls = findUrls(value.text());
        
        Section section = new Section();
        section.title = label.text();
        section.link = listOfUrls;
        
        return section;
    }
    
    private ScienceOrFiction parseScienceOrFiction(Element header) {
        Elements labels = header.select("span.podcast-item-label");
        Elements values = header.select("span.podcast-item-value");
        
        ScienceOrFiction sof = new ScienceOrFiction();
        sof.listOfItem = new ArrayList<>();
        
        for (int i = 0; i < labels.size(); i++) {
            Element value = values.get(i);
            
            Item item = new ScienceOrFiction.Item();
            item.title = labels.get(i).text().trim();
            item.description = findTitle(value.text()).trim();
            item.link = findUrls(value.text());
            
            sof.listOfItem.add(item);
        }
        
        return sof;
    }
    
    private Episode.Quote parseQuote(Element header) {
        //Element label = header.select("span.podcast-item-label").first();
        Element value = header.select("span.podcast-item-value").first();

        String text = value.text().trim();
        String first = text.substring(0, 1);
        
        int index = 0;
        int end = -1;
        
        if (first.equalsIgnoreCase("'") || first.equalsIgnoreCase("\"") || first.equalsIgnoreCase("Ò")) {
            index++;
            end = text.lastIndexOf(first);
            if (end < 1)
                end = text.lastIndexOf("Ó");
        }
        
        String q = text.substring(index, end);
        
        int inde1 = text.indexOf("Ñ");
        if (inde1 < 0)
            inde1 = text.indexOf("~");
        if (inde1 < 0)
            inde1 = text.indexOf("-");
        
        String name = text.substring(++inde1).trim();
        
        Episode.Quote quote = new Quote();
        quote.by = name;
        quote.text = "\"" + q + "\"";
        
        return quote;
    }

    private ArrayList<String> findUrls(String text) {
        ArrayList<String> listOfUrls = new ArrayList<String>();
        
        int index = text.indexOf("http://");
        
        if (index > -1) {
            int end = text.indexOf(" ", index);
            
            if (end == -1)
                end = text.length();
            
            listOfUrls.add(text.substring(index, end));
            
            index = text.indexOf("http://", end);
        }
        
        return listOfUrls;
    }
    
    /**
     * @param resp
     * @param status
     * @param message
     * @throws IOException
     */
    private void printError(HttpServletResponse resp, String status, String message) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(FetchResponse.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            FetchResponse fetch = new FetchResponse();
            fetch.status = status;
            fetch.message = message;

            m.marshal(fetch, resp.getOutputStream());

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
