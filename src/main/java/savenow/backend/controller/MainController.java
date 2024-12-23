package savenow.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import savenow.backend.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/main")
public class MainController {

    private final UserService userService;

    // 생성자를 통한 의존성 주입
    public MainController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 메인 페이지 데이터를 반환합니다.
     * 로그인한 유저의 닉네임을 가져옵니다.
     *
     * @param authentication Spring Security에서 제공하는 인증 객체
     * @return 유저의 닉네임을 포함한 응답
     */
    @GetMapping
    public ResponseEntity<Map<String, String>> getMainPageData(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body(Map.of("message", "로그인이 필요합니다."));
        }

        // 인증 객체에서 유저 이메일 추출
        String email = authentication.getName();
        // 이메일을 통해 닉네임 조회
        String nickname = userService.getNicknameByEmail(email);

        return ResponseEntity.ok(Map.of("nickname", nickname));
    }
}
