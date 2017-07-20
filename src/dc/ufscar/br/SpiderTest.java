package dc.ufscar.br;

import java.util.concurrent.TimeUnit;

/**
 * Executes the test that creates a spider that creates spider legs
 * and crawls the web.
 */


public class SpiderTest {

    public static void main(String[] args) throws InterruptedException {
        Spider spider = new Spider();
        //spider.search("http://arstechnica.com", "computer");
        //spider.test();
        String KEYWORD = "google";

        while(true){
            String url = spider.requestUrl();
            if (url.isEmpty()){
                TimeUnit.MINUTES.sleep(1);
            } else {
                spider.search("url", KEYWORD);
            }
        }
    }
}
