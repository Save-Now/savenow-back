package savenow.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.entity.user.User;
import savenow.backend.service.UserProfileService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class UserProfileController {

    private final UserProfileService userService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getProfile(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(Map.of(
                "profile_picture", user.getProfilePicture(),
                "username", user.getUsername(),
                "nickname", user.getNickname(),
                "birth", user.getBirth()
        ));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> updateProfile(@RequestParam Long userId, @RequestBody Map<String, String> updates) {
        User updatedUser = userService.updateUserProfile(userId, updates);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "프로필 정보가 성공적으로 수정되었습니다.",
                "updated_data", Map.of(
                        "profile_picture", updatedUser.getProfilePicture(),
                        "username", updatedUser.getUsername(),
                        "nickname", updatedUser.getNickname(),
                        "birth", updatedUser.getBirth()
                )
        ));
    }

    @PatchMapping("/password")
    public ResponseEntity<Map<String, Object>> updatePassword(@RequestParam Long userId, @RequestBody Map<String, String> updates) {
        userService.updateUserPassword(userId, updates.get("password"));
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "비밀번호가 성공적으로 변경되었습니다."
        ));
    }
}