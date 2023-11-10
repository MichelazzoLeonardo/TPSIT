package WebCrawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
    private String rootUrl;
    private int depth;

    public WebCrawler(String startUrl, int depth) {
        this.rootUrl = startUrl;
        this.depth=depth;
    }

    public void startCrawling() {
        try {
            crawlPage(rootUrl, depth);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crawlPage(String url, int depth) throws IOException {
        if (depth <= 0)
            return;

        System.out.println("Crawling: " + url);

        Document document = Jsoup.connect(url).get();

        Elements refs = document.select("a[href]");
        for (Element ref : refs) {
            String link = ref.absUrl("href");

            System.out.println("New Link found: " + link);

            depth--;
            crawlPage(link, depth);
        }
    }
}
