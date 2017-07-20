package dc.ufscar.br;

import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class SpiderLeg {

    // To trick the web server that the robot is a normal web browser
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; " +
            "WOW64 AppleWebKit/535.1 (KHTML, like Gecko) " +
            "Chrome/13.0.782/112 Safari/535.1";

    // List of URLs
    private List<String> links = new LinkedList<String>();

    // Our webpage (document)
    private Document htmlDocument;

    /*
     * Makes most of the work. Makes an HTTP request, checks the response and then
     * gathers all the links on the page. Perform a searchForWord after the successful
     * crawl.
     *
     * Parameters:
     * String url - The url to visit
     *
     */

    public boolean crawl(String url){
        try {

            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;

            // HTTP Code 200 ís the OK Status
            if (connection.response().statusCode() == 200){
                System.out.println("\nWORKING... ");
                System.out.println("Received web page at " + url + "");
            }

            if(!connection.response().contentType().contains("text/html")){
                System.out.println("FAILURE!");
                System.out.println("Retrieved something different than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            System.out.println("Found (" + linksOnPage.size() + ") links");

            for(Element link : linksOnPage){
                this.links.add(link.absUrl("href"));
            }

            return true;

        } catch(IOException ie){
            System.out.println("ERROR! Couldn't make an HTTP request.");
            return false;
        }
    }

    /*
     * Searches the HTML Document retrieved for the searchWord. Only called
     * after a successful crawl.
     *
     * Parameters:
     * String SearchWord - The string to search in the HTML document
     *
     */

    public boolean searchForWord(String searchWord){

        // To avoid any error, probably won't enter this case
        // because this method only should be called after a
        // successful crawl. Exaggerating.
        if(this.htmlDocument == null){
            System.out.println("ERROR! This method should be called after the" +
                    "crawl() method call");
            return false;
        }

        System.out.println("Searching for the word " + searchWord + "...");
        String bodyText = this.htmlDocument.body().text();

        return bodyText.toLowerCase().contains(searchWord.toLowerCase());
    }

    public List<String> getLinks(){
        return this.links;
    }
}
