package savenow.backend.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final String CLIENT_ID = "d9c739e17449e17b9470514b27f11ceb";
    private final String REDIRECT_URI = "http://localhost:8080/callback";
    private final String TOKEN_URI = "https://kauth.kakao.com/oauth/token";
    private final String USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";

    @GetMapping("/login/kakao")
    public ResponseEntity<Map<String, String>> getKakaoAuthUrl() {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?" +
                "response_type=code" +
                "&client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI;
        Map<String, String> response = new HashMap<>();
        response.put("url", kakaoAuthUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/callback")
    public ResponseEntity<Map<String, Object>> callback(@RequestParam("code") String code) {
        RestTemplate restTemplate = new RestTemplate();

        // Access Token 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> tokenRequest = new LinkedMultiValueMap<>();
        tokenRequest.add("grant_type", "authorization_code");
        tokenRequest.add("client_id", CLIENT_ID);
        tokenRequest.add("redirect_uri", REDIRECT_URI);
        tokenRequest.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(tokenRequest, headers);
        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(TOKEN_URI, request, Map.class);
        String accessToken = (String) tokenResponse.getBody().get("access_token");

        // 사용자 정보 요청
        HttpHeaders userHeaders = new HttpHeaders();
        userHeaders.set("Authorization", "Bearer " + accessToken);

        HttpEntity<Void> userInfoRequest = new HttpEntity<>(userHeaders);
        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
                USER_INFO_URI,
                HttpMethod.GET,
                userInfoRequest,
                Map.class
        );

        // 사용자 정보 및 Access Token 반환
        Map<String, Object> response = new HashMap<>();
        response.put("userInfo", userInfoResponse.getBody());
        response.put("accessToken", accessToken);

        return ResponseEntity.ok(response);
    }
}
