
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;
import java.util.concurrent.RecursiveAction;

public class Parser extends RecursiveAction {

    private String URL;
    private Queue<String> parsedLinks;


    public Parser(String url, Queue<String> links){
        parsedLinks = links;
        URL = url;
    }

    @Override
    protected void compute() {
        if (!parsedLinks.contains(URL)) {


            parsedLinks.add(URL);

            Document doc = null;

            try {
                doc = Jsoup
                        .connect(URL)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                        .timeout(0)
                        .ignoreHttpErrors(true)
                        .get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Elements titles = doc.select("a[href^=https://skillbox.ru/]");

            for (Element element : titles) {
                String link = element.absUrl("href").substring(0, element.absUrl("href").lastIndexOf("/") + 1);
                System.out.println(link);
                Parser p = new Parser(link, parsedLinks);
                p.compute();
            }

        }







    }
}