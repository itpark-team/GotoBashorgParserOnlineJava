package org.example.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BashorgParser {
    public String getRandomJoke() throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://bashorg.org/casual"))
                .header("User-Agent","Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .build();

        HttpResponse httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String bodyAsString = (String) httpResponse.body();

        Document document = Jsoup.parse(bodyAsString);
        Element divq = document.select("div.q").first();
        String plainHtml = divq.select("div").last().html();

        plainHtml = plainHtml.replace("<br>","\n").replace("&quot;","\"").replace("&lt;","<").replace("&gt;",">");

        return plainHtml;
    }
}
