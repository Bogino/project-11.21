import java.util.concurrent.ForkJoinPool;

public class Processor {

    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        Parser parser = new Parser("https://skillbox.ru/");
        fjp.invoke(parser);
    }
}
