package dc.ufscar.br;

/**
 * Executes the test that creates a spider that creates spider legs
 * and crawls the web.
 */
public class SpiderTest {

    public static void main(String[] args){
        Spider spider = new Spider();
        spider.search("http://arstechnica.com", "computer");
        //spider.test();
    }
}
