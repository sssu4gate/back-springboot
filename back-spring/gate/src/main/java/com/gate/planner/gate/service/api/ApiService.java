package com.gate.planner.gate.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gate.planner.gate.dao.user.UserRepository;
import com.gate.planner.gate.exception.user.UserNotExistException;
import com.gate.planner.gate.model.dto.api.ProfileApiDto;
import com.gate.planner.gate.model.dto.api.TokenRefreshDto;
import com.gate.planner.gate.model.dto.place.PlaceDto;
import com.gate.planner.gate.model.entity.user.User;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Value("${kakao.api.refresh.token.url}")
    private String KAKAO_REFRESH_TOKEN_URL;

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    public ArrayList<PlaceDto> callLocationAPI(int page, String keyword, int offset) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + APIKEY);
        entity = new HttpEntity<String>(headers);
        ArrayList<PlaceDto> result = new ArrayList<>();
        String url = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCATION_URL).queryParam("query", keyword).queryParam("size", offset).queryParam("page", page).build().toUriString();
        ArrayList<LinkedHashMap> list = (ArrayList<LinkedHashMap>) restTemplate.exchange(url, HttpMethod.GET, entity, JSONObject.class).getBody().get("documents");

        for (LinkedHashMap value : list)
            result.add(objectMapper.readValue(JSONObject.toJSONString(value), PlaceDto.class));

        return result;
    }
    public ProfileApiDto callUserInfoAPI(String accessToken, String refreshToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        entity = new HttpEntity<String>(headers);

        try {
            ResponseEntity<JSONObject> ret = restTemplate.exchange(KAKAO_PROFILE_URL, HttpMethod.GET, entity, JSONObject.class);
            return objectMapper.readValue(ret.getBody().toJSONString(), ProfileApiDto.class);
        } catch (Exception e) {
            refreshTokenApi(refreshToken);
            return objectMapper.readValue(restTemplate.exchange(KAKAO_PROFILE_URL, HttpMethod.GET, entity, JSONObject.class).getBody().toJSONString(), ProfileApiDto.class);
        }
    }

    public ResponseEntity<JSONObject> callLoginAPI(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", APIKEY);
        params.add("redirect_uri", BASE_URL + "/auth/kakao/login");
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

    public User refreshTokenApi(String refreshToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("grant_type", "refresh_token");
        params.add("client_id", APIKEY);
        params.add("refresh_token", refreshToken);

        entity = new HttpEntity(params, headers);
        TokenRefreshDto tokenRefreshDto = objectMapper.readValue((restTemplate.exchange(KAKAO_REFRESH_TOKEN_URL, HttpMethod.POST, entity, JSONObject.class).getBody().toJSONString()), TokenRefreshDto.class);

        headers.set("Authorization", "Bearer " + tokenRefreshDto.getAccess_token());
        entity = new HttpEntity(headers);
        ProfileApiDto profile = objectMapper.readValue(restTemplate.exchange(KAKAO_PROFILE_URL, HttpMethod.GET, entity, JSONObject.class).getBody().toJSONString(), ProfileApiDto.class);

        User user = userRepository.findById(profile.getId()).orElseThrow(UserNotExistException::new);

        if (tokenRefreshDto.getRefresh_token() != null)
            user.setRefreshToken(tokenRefreshDto.getRefresh_token());
        user.setAccessToken(tokenRefreshDto.getAccess_token());

        return user;
    }
}
