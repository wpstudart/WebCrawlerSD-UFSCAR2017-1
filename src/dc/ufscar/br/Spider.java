package dc.ufscar.br;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Spider {

    private static final int MAX_PAGE_SEARCH = 10; // Max number of pages to search
    private Set<String> pagesVisited = new HashSet<String>(); // All pages visited will be unique
    private List<String> pagesToVisit = new LinkedList<String>(); // URL collected by the Crawler

    /*
     * Launching Point for the WebCrawler's (Spider) functionality.
     * It creates spider legs that makes HTTP requests and parse the
     * web page returned.
     *
     * Parameters:
     * String url - Starting point for the Spider
     * String searchWord - The keyword the Webcrawler will search for
     *
     */
    public void search(String url, String searchWord){
        while(this.pagesVisited.size() < MAX_PAGE_SEARCH){
            String currentURL;
            SpiderLeg leg = new SpiderLeg();
            if (this.pagesToVisit.isEmpty()){
                currentURL = url;
                this.pagesVisited.add(url);
            } else {
                currentURL = this.nextURL();
            }
            leg.crawl(currentURL); // Check the crawl method in Spider Leg

            boolean success = leg.searchForWord(searchWord);
            if(success){
                System.out.println(String.format(" SUCCESS! Word %s found at %s",searchWord,currentURL));
                break;
            }
            this.pagesToVisit.addAll(leg.getLinks());

        }

        System.out.println("\n DONE! Visited " + this.pagesVisited.size() + " web page(s)");
    }

    /*
     * Get the first Entry from pagesToVisit, make sure that URL isn't in the set of URL already visited
     * and then return it.
     */

    private String nextURL(){

        String nextURL;

        do {
            nextURL = this.pagesToVisit.remove(0);
        } while(this.pagesVisited.contains(nextURL));

        return nextURL;
    }

    /*
     *
     */


}
