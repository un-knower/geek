package com.tzp.T.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * author i
 * date 2016/7/15 16:58
 */
public class PicDown {
    public static void main(String[] args) throws IOException {
        String url = "http://www.i4.cn/";
        Document html = Jsoup.connect(url).get();
        Elements imgs = html.select(".apps img[src]");
        for(Element e:imgs){
            System.out.println(e.attr("abs:src"));
        }
    }
}
