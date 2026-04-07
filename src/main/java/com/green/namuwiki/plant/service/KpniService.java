package com.green.namuwiki.plant.service;

import com.green.namuwiki.plant.dto.KpniRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class KpniService {
    private static final String KPNI_URL = "https://apis.data.go.kr/1400119/KpniService/scnmSearch?serviceKey=";
    private final RestClient restClient;

    @Value("${kpni.api-key}")
    private String apiKey;

    public KpniRes findKorInfo(String scientificName) {
        try {
            List<KpniRes> results = search(1, 1, scientificName);
            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            log.warn("KPNI 한글 정보 조회 실패: {}", e.getMessage());
            return null;
        }
    }

    public List<KpniRes> search(int pageNo, int numOfRows, String name) {
        try {
            byte[] bytes = restClient.get()
                    .uri(URI.create(KPNI_URL + apiKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows + "&reqScnm=" + URLEncoder.encode(name, StandardCharsets.UTF_8)))
                    .retrieve()
                    .body(byte[].class);

            String xml = new String(bytes, StandardCharsets.UTF_8);
            return parseXml(xml);
        } catch (Exception e) {
            log.error("KPNI API 호출 실패: {}", e.getMessage());
            throw new RuntimeException("식물 정보 조회에 실패했습니다.");
        }
    }

    private List<KpniRes> parseXml(String xml) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));

        NodeList items = doc.getElementsByTagName("item");
        List<KpniRes> result = new ArrayList<>();

        for (int i = 0; i < items.getLength(); i++) {
            Node node = items.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;

            Element el = (Element) node;
            result.add(new KpniRes(
                    getText(el, "classKorNm"),
                    getText(el, "falmKorNm"),
                    getText(el, "genusKorNm"),
                    getText(el, "ordKorNm"),
                    getText(el, "phylumKorNm"),
                    getText(el, "plantGnrlNm"),
                    getText(el, "plantSpecsScnm")
            ));
        }

        return result;
    }

    private String getText(Element el, String tagName) {
        NodeList nodes = el.getElementsByTagName(tagName);
        return nodes.getLength() > 0 ? nodes.item(0).getTextContent() : null;
    }
}
