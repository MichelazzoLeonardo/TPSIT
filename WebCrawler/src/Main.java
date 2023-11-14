import WebCrawler.WebCrawler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan= new Scanner(System.in);

        String rootUrl;
        int depth;

        System.out.println("Insert the rootUrl:");
        rootUrl=scan.nextLine();
        System.out.println("Insert the searching depth:");
        depth=scan.nextInt();

        WebCrawler crawler = new WebCrawler(rootUrl, depth);
        crawler.Crawl();
    }
}
