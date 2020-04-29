import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Processor {


    public static void main(String[] args) {



        Queue<String> links = new LinkedList<>();
        String URL = "https://skillbox.ru/";
        String domen = "//skillbox.ru/";
        Parser p = new Parser(URL, links, domen);
        ForkJoinPool fjp = ForkJoinPool.commonPool();
        fjp.invoke(p);


    }
}
