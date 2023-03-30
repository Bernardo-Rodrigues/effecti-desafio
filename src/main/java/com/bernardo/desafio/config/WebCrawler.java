package com.bernardo.desafio.config;

import com.bernardo.desafio.model.entities.Bid;
import com.bernardo.desafio.model.entities.Edict;
import com.bernardo.desafio.model.entities.Modality;
import com.bernardo.desafio.repositories.BidRepository;
import com.bernardo.desafio.repositories.EdictRepository;
import com.bernardo.desafio.repositories.ModalityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.IOException;
import java.util.stream.IntStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Configuration
public class WebCrawler implements CommandLineRunner {
    final String HOME_PATH = "https://www.agrolandia.sc.gov.br";
    final String BIDS_MODALITIES_PATH = HOME_PATH + "/licitacoes";
    final List<String> MONTHS = new ArrayList<>(Arrays.asList("jan", "fev", "mar", "abr", "mai", "jun", "jul", "ago", "set", "out", "nov", "dez"));
    private static final Logger LOGGER = LoggerFactory.getLogger(WebCrawler.class);
    List<String> tableColumns = new ArrayList<>();

    @Autowired
    ModalityRepository modalityRepository;
    @Autowired
    BidRepository bidRepository;
    @Autowired
    EdictRepository edictRepository;

    @Override
    public void run(String... args)  {
        navigateModalitiesPage();
        LOGGER.info("Web crawling complete");
    }

    private void navigateModalitiesPage(){
        Document modalitiesPage = jsoupConnect(BIDS_MODALITIES_PATH);

        Element modalitiesTable = modalitiesPage.getElementsByTag("table").first();
        modalitiesTable.getElementsByTag("th").forEach(
                (th) -> tableColumns.add(th.text().toLowerCase())
        );
        modalitiesTable.getElementsByTag("tr").forEach(
                (tr) -> {
                    String modalityName = tr.getElementsByClass("titulo").text();

                    Elements columns = tr.getElementsByTag("td");

                    IntStream.range(0, columns.size()).forEach( index -> {
                        Element column = columns.get(index);
                        boolean hasLink = !column.getElementsByTag("a").isEmpty();
                        boolean reportedOrInProgress = index == tableColumns.indexOf("divulgado") || index == tableColumns.indexOf("andamento");

                        if(hasLink && reportedOrInProgress) {
                            String modalityPath = columns.get(index).getElementsByTag("a").attr("href");
                            navigateModalityPage(modalityPath, modalityName);
                        }
                    });
                }
        );
    }

    private void navigateModalityPage(String modalityBidsPath, String modalityName){
        Document modalityPage = jsoupConnect(HOME_PATH + modalityBidsPath);

        modalityPage.getElementsByClass("item-lista").first()
                .getElementsByTag("li").forEach(
                        (li) -> {
                            boolean isNavigationButtonNexPage = li.hasClass("proximo");
                            boolean isBid = li.getElementsByTag("div").hasClass("data");

                            if (isNavigationButtonNexPage) {
                                navigateModalityPage(li.getElementsByTag("a").attr("href"), modalityName);
                            } else if (isBid) {
                                String BidPath = li.getElementsByClass("nome-objeto").first().getElementsByTag("a").attr("href");
                                Document bidPage = jsoupConnect(HOME_PATH + BidPath);

                                Element bidDetails = bidPage.getElementsByClass("licitacoes detalhes").first();
                                Element basicInfo = bidPage.getElementsByClass("info-basicas").first();

                                String name = bidDetails.getElementsByTag("h2").text();
                                String day = basicInfo.getElementsByClass("dia").text();
                                String month = basicInfo.getElementsByClass("mes").text();
                                String year = basicInfo.getElementsByClass("ano").text();
                                String description = extractNullableInformation(basicInfo.getElementsByClass("objeto").first());
                                String entity = extractNullableInformation(basicInfo.getElementsByClass("entidade").first());
                                String sector = extractNullableInformation(basicInfo.getElementsByClass("setor").first());
                                String local = extractNullableInformation(basicInfo.getElementsByClass("local").first());
                                Double value = extractValueInformation(basicInfo.getElementsByClass("valor").first());

                                Modality modality = modalityRepository.findByName(modalityName);
                                Bid bid = Bid.builder()
                                        .modality(modality)
                                        .name(name)
                                        .openingDate(formatDate(day, month, year))
                                        .description(description)
                                        .entity(entity)
                                        .sector(sector)
                                        .local(local)
                                        .value(value)
                                        .build();
                                Bid savedBid = bidRepository.save(bid);

                                Element bidEdictsElement = bidPage.getElementsByClass("docs").first();
                                bidEdictsElement.getElementsByTag("a").forEach(
                                        (a) -> {
                                            String link = a.attr("href");
                                            String edictName = a.getElementsByTag("strong").text();
                                            Edict edict = Edict.builder()
                                                    .link(HOME_PATH + link)
                                                    .name(edictName)
                                                    .bid(savedBid)
                                                    .build();
                                            edictRepository.save(edict);
                                        }
                                );
                            }
                        }
                );
    }

    private String extractNullableInformation(Element element){
        if(element != null) {
            return element.text().replaceAll("(Objeto|Entidade|Local|Setor responsável):\\s+", "");
        }
        return null;
    }

    private Double extractValueInformation(Element element){
        if(element != null) {
            return Double.valueOf(element.text().replaceAll("[^0-9,]+", "").replace(',', '.'));
        };
        return null;
    }

    private LocalDate formatDate(String day, String month, String year){
        if(MONTHS.indexOf(month) + 1 < 10) month = "0" + (MONTHS.indexOf(month) + 1);
        else month = String.valueOf(MONTHS.indexOf(month) + 1);

        try {
            return LocalDate.parse(year + "-" + month + "-" + day);
        } catch (DateTimeParseException e){
            return null;
        }
    }

    private Document jsoupConnect(String path){
        try {
            return Jsoup.connect(path).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
