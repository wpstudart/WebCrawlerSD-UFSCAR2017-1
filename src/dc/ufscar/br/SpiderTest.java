package dc.ufscar.br;

import com.sun.xml.internal.org.jvnet.fastinfoset.EncodingAlgorithmException;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * Executes the test that creates a spider that creates spider legs
 * and crawls the web.
 */


public class SpiderTest {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        Spider spider = new Spider();
        //spider.search("http://arstechnica.com", "computer");
        //spider.test();
        String KEYWORD = "temer";
        String url = "http://terra.com.br";

        while(true) {

            System.out.println(url);
            //String encodedUrl = java.net.URLEncoder.encode(url, "UTF-8");
            spider.search(url, KEYWORD);
            url = spider.requestUrl();

        }
    }
}
