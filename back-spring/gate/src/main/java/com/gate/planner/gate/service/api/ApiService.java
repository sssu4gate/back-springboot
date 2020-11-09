package com.gate.planner.gate.service.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class ApiService {
    HttpEntity entity;
    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.url.base}")
    String BASE_URL;

    @Value("${kakao.api.key}")
    String APIKEY;

    @Value("${kakao.api.profile.url}")
    private String KAKAO_PROFILE_URL;

    @Value("${kakao.api.location.url}")
    private String KAKAO_LOCATION_URL;

    @Value("${kakao.api.login.url}")
    private String KAKAO_LOGIN_URL;

    @Value("${kakao.api.logout.url}")
    private String KAKAO_LOGOUT_URL;

    @Value("${kakao.api.tokeninfo.url}")
    private String KAKAO_TOKEN_INFO_URL;

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public ArrayList<PlaceDto> callLocationAPI(int page, String keyword) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + APIKEY);
        entity = new HttpEntity<String>(headers);
        ArrayList<PlaceDto> result = new ArrayList<>();
        String url = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCATION_URL).queryParam("query", keyword).queryParam("size", 5).queryParam("page", page).build().toUriString();
        ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class).getBody().get("documents");

        for (LinkedHashMap value : list)
            result.add(objectMapper.readValue(JSONObject.toJSONString(value), PlaceDto.class));

        return result;
    }

    public ResponseEntity<JSONObject> callUserInfoAPI(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<String>(headers);

        return restTemplate.exchange(KAKAO_PROFILE_URL, HttpMethod.GET, entity, JSONObject.class);
    }

    public ResponseEntity<JSONObject> callLoginAPI(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", APIKEY);
        params.add("redirect_uri", BASE_URL + "/auth/login");
        params.add("code", code);
        entity = new HttpEntity(params, headers);

        return restTemplate.exchange(KAKAO_LOGIN_URL, HttpMethod.POST, entity, JSONObject.class);
    }

    public void callLogOutAPI(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        entity = new HttpEntity(headers);
        restTemplate.exchange(KAKAO_LOGOUT_URL, HttpMethod.POST, entity, JSONObject.class);
    }

    public void callTokenInfo(String accessToken, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer " + accessToken);
        entity = new HttpEntity(headers);

        ResponseEntity<Object> response = restTemplate.exchange(KAKAO_TOKEN_INFO_URL, HttpMethod.GET, entity, Object.class);
        if (response.getStatusCode().value() == 400 || response.getStatusCode().value() == 401) {
            /**
             400은 잘못된 토큰형식, 401은 만료
             Seucirty ContextHolder 가져와서 User객체를 찾은다음
             accessToken과 refeshToken을 바꿔주기
             */

        } else if (response.getStatusCode().value() == 200) {
            /**
             *  정상실행된것
             */
        } else {
            /**
             * 알수 없는 오류 발생 시 에러 리턴 (재 로그인 필요).
             */
        }
        /**
         * return 정상 accessToken
         */
    }
}
