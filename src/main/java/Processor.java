import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Processor {


    static final int count = 0;

    public static void main(String[] args) {

        try {

        Queue<String> links = new LinkedList<>();
        String URL = "https://skillbox.ru/";
        String domain = "//skillbox.ru/";
        String tab = "";

        PrintWriter writer = new PrintWriter("data/URLs.txt");

        Parser p = new Parser(URL, links, domain, writer, tab, count);
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        fjp.invoke(p);
        writer.flush();
        writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
