package com.gate.planner.gate.service;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class ApiService {
    HttpEntity<String> entity;

    @Value("${kakao.api.key}")
    String APIKEY;

    String URL = "https://dapi.kakao.com/v2/local/search/keyword.json";

    @PostConstruct
    public void setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + APIKEY);
        entity = new HttpEntity<>(headers);
    }

    private final RestTemplate restTemplate;

    public JSONObject RequestAPI(int page, String keyword) throws UnsupportedEncodingException {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(URL).queryParam("query", keyword).queryParam("size", 5).queryParam("page", page).build();
        String url = uriComponents.toUriString();
        return restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class).getBody();

    }
}
