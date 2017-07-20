package dc.ufscar.br;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
                uploadUrl(url);
            } else {
                currentURL = this.nextURL();
            }
            leg.crawl(currentURL); // Check the crawl method in Spider Leg

            boolean success = leg.searchForWord(searchWord);
            if(success){
                System.out.println(String.format(" SUCCESS! Word %s found at %s",searchWord,currentURL));
                updateUrl(currentURL, searchWord);
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

    //Essa funcao faz a requisição de uma url para o servidor e retorna a url na forma de string
    public String requestUrl() {
        String ip = "http://projetosd.ddns.net:8080/phpserver";
    //    String ip = "192.168.0.103/webapp";

        String reg_url = ip + "/requesturl.php";

        try {
            URL url = new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream), "iso-8859-1"));
            String result = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            JSONObject obj = new JSONObject(result);
            String newurl = obj.getString("url");
            return newurl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //Esta função faz a requisição para o upload de uma nova url no servidor
    public void uploadUrl(String newurl){
        String ip = "http://projetosd.ddns.net:8080/phpserver";
        //    String ip = "192.168.0.103/webapp";

        try{
            URL url = new URL(ip + "/inserturl.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("url","UTF-8") + "=" + URLEncoder.encode(newurl,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream  = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream),"iso-8859-1"));
            String result = "";
            String line = "";
            while((line = bufferedReader.readLine())!= null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return;

        }
        catch (Exception e){e.printStackTrace();}
    }

    public void updateUrl(String newurl, String keyword)
    {
        String ip = "http://projetosd.ddns.net:8080/phpserver";
        //    String ip = "192.168.0.103/webapp";

        try{
            URL url = new URL(ip + "/updateurl.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("url","UTF-8") + "=" + URLEncoder.encode(newurl,"UTF-8")  + "&" +
                    URLEncoder.encode("keyword","UTF-8")  + "=" + URLEncoder.encode(keyword,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream  = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream),"iso-8859-1"));
            String result = "";
            String line = "";
            while((line = bufferedReader.readLine())!= null){
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return;

        }
        catch (Exception e){e.printStackTrace();}
    }


    public void test(){

    }


}
