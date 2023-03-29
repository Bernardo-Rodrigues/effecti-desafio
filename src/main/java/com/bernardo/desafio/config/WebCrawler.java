package com.bernardo.desafio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.util.stream.IntStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Configuration
public class WebCrawler implements CommandLineRunner {
    final String HOME_PATH = "https://www.agrolandia.sc.gov.br";
    final String ALL_BIDS_PATH = HOME_PATH + "/licitacoes";
    final List<String> TABLE_COLUMNS = new ArrayList<>(Arrays.asList("Modalidades", "divulgado", "andamento", "encerrada", "suspenso", "Total"));

    @Override
    public void run(String... args) throws Exception {
        Document allBidsPage = Jsoup.connect(ALL_BIDS_PATH).get();

        allBidsPage.getElementsByTag("table").first()
                .getElementsByTag("tr").forEach(
                        (tr) -> {
                            String modality = tr.getElementsByClass("titulo").text();
                            Elements columns = tr.getElementsByTag("td");

                            IntStream.range(0, columns.size()).forEach( index -> {
                                Element column = columns.get(index);
                                if(
                                        !column.getElementsByTag("a").isEmpty()
                                                && ( index == TABLE_COLUMNS.indexOf("divulgado") || index == TABLE_COLUMNS.indexOf("andamento"))
                                ) {
                                    System.out.println(modality + " - " + TABLE_COLUMNS.get(index));
                                    String modalityBidsPath = columns.get(index).getElementsByTag("a").attr("href");

                                    navigateModalityPage(modalityBidsPath);
                                }
                            });
                        }
                );
    }

    private void navigateModalityPage(String modalityBidsPath){
        Document modalityPage = null;
        try {
            modalityPage = Jsoup.connect(HOME_PATH + modalityBidsPath).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        modalityPage.getElementsByClass("item-lista").first()
                .getElementsByTag("li").forEach(
                        (li) -> {
                            if (!li.hasClass("anterior") && !li.hasClass("paginaAtiva") && (li.getElementsByTag("span").isEmpty() || li.getElementsByTag("span").first().getElementsByTag("a").isEmpty())) {
                                if (li.getElementsByTag("a").text().equals("Próxima")) {
                                    navigateModalityPage(li.getElementsByTag("a").attr("href"));
                                } else {
                                    String BidPath = li.getElementsByClass("nome-objeto").first().getElementsByTag("a").attr("href");
                                    try {
                                        Document bidPage = Jsoup.connect(HOME_PATH + BidPath).get();
                                        System.out.println(bidPage.getElementsByClass("licitacoes detalhes").first());
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                );
    }
}
