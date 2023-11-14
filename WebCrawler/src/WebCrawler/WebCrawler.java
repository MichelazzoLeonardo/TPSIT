package WebCrawler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
    private String rootUrl;
    private int searchDepth;

    public WebCrawler(String startUrl, int depth) {
        if (!startUrl.startsWith("https://") && !startUrl.startsWith("http://"))
            rootUrl = "https://" + startUrl;
        else
            rootUrl = startUrl;
        searchDepth = depth;
    }

    public void Crawl() {
        System.out.println("Crawling: " + rootUrl);
        try {
            crawlPage(rootUrl, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void crawlPage(String url, int depth) throws IOException {
        if (depth < searchDepth) {
            Document document = Jsoup.connect(url).get();

            Elements refs = document.select("a[href]");
            for (Element ref : refs) {
                String link = ref.absUrl("href");

                for (int i = 0; i < depth; i++)
                    System.out.print("\t");
                System.out.println(depth + ": " + link);

                crawlPage(link, depth+1);
            }
        }
    }
}
