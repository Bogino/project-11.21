
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.RecursiveAction;

public class Parser extends RecursiveAction {

    private String URL;
    private Queue<String> parsedLinks;
    private String domain;
    private PrintWriter writer;
    private String tab;
    private int count;


    public Parser(String url, Queue<String> links, String domain, PrintWriter writer, String tab, int count){
        parsedLinks = links;
        URL = url;
        this.domain = domain;
        this.writer = writer;
        this.tab = tab;
        this.count = count;
    }

    @Override
    protected void compute() {
        if (!parsedLinks.contains(URL)) {

            try {
                writer.write(tab + URL + "\n");
                count++;
                for(int i = 0; i <count; i++){
                    tab+="\t";
                }

                parsedLinks.add(URL);

                Document doc = null;

                doc = Jsoup
                        .connect(URL)
                        .ignoreContentType(true)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36")
                        .timeout(0)
                        .ignoreHttpErrors(true)
                        .get();


            Elements titles = doc.select("a[href^=https:" + domain + "]");

            for (Element element : titles) {
                String link = element.absUrl("href");
                if (link.equals("")){
                    continue;
                }
                System.out.println(link);
                Parser p = new Parser(link, parsedLinks, domain, writer, tab, count);
                p.compute();
            }

        } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}