package savenow.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    // 메인 페이지 - 로그인 여부에 따라 다른 응답
    @GetMapping("/main")
    public String mainPage(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && !authHeader.isEmpty()) {
            return "닉네임 버튼 (마이 페이지로 이동)";
        } else {
            return "로그인 버튼";
        }
    }
}

