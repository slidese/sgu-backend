package se.slide.sgu.metadata;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveMetadataServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -4883766741923209409L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException {
        
        //String name = req.getParameter("guestbookName");
        
        String number = "446";
        
        Entity greeting = new Entity("Episode", number);
        greeting.setProperty("number", number);
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(greeting);

        resp.sendRedirect("/index.jsp?episode=" + number);
    }
}
