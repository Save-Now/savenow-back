package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import savenow.backend.entity.user.User;
import savenow.backend.entity.user.UserRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUserProfile(Long userId, Map<String, String> updates) {
        User user = getUserById(userId);
        user.updateProfile(
                updates.get("profile_picture"),
                updates.get("username"),
                updates.get("nickname"),
                updates.get("birth"),
                updates.get("password")
        );
        return userRepository.save(user);
    }

    public void updateUserPassword(Long userId, String newPassword) {
        User user = getUserById(userId);
        user.updatePassword(newPassword);
        userRepository.save(user);
    }
}

