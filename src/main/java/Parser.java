import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.RecursiveAction;

public class Parser extends RecursiveAction {
    private String URL;

    public Parser(String url){
        URL = url;
    }

    @Override
    protected void compute() {
        Document doc = null;

        try {
            doc = Jsoup.connect(URL).maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements titles = doc.select("a[href^=https://skillbox.ru/]");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("data/URLs.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Element element : titles){
            writer.write(element.absUrl("href").substring(0,element.absUrl("href").lastIndexOf("/")+1) + "\n");
            try {
                for (Element el : Jsoup.connect(element.absUrl("href").substring(0,element.absUrl("href").lastIndexOf("/")+1)).maxBodySize(0).get().select("a[href^=https://skillbox.ru/]")){
                   writer.write("\t" + el.absUrl("href") + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writer.flush();
        writer.close();

    }

}

