package savenow.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.UpdateUserRequest;
import savenow.backend.dto.UserDTO;
import savenow.backend.service.UserService;

@RestController
@RequestMapping("/user")
public class MyPageController {

    private final UserService userService;

    // 생성자를 통해 의존성 주입
    public MyPageController(UserService userService) {
        this.userService = userService;
    }

    // 마이 페이지 정보 가져오기
    @GetMapping("/mypage/{id}")
    public ResponseEntity<UserDTO> getMyPage(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    // 마이 페이지 정보 수정
    @PutMapping("/mypage/{id}")
    public ResponseEntity<String> updateMyPage(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        try {
            userService.updateUser(id, updateUserRequest);
            return ResponseEntity.ok("유저 정보가 수정되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
