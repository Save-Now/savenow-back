package savenow.backend.service;

import org.springframework.stereotype.Service;
import savenow.backend.dto.UpdateUserRequest;
import savenow.backend.dto.UserDTO;
import savenow.backend.entity.User;
import savenow.backend.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ID로 유저 조회
    public UserDTO getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User foundUser = user.get();
            return new UserDTO(
                    foundUser.getUsername(),
                    foundUser.getEmail(),
                    foundUser.getBirth(),
                    foundUser.getGender().getValue()
            );
        } else {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }
    }

    // 유저 정보 업데이트
    public void updateUser(Long id, UpdateUserRequest updateUserRequest) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // 필드를 개별적으로 업데이트
            user.setUsername(updateUserRequest.getNewUsername());
            user.setPassword(updateUserRequest.getNewPassword());
            userRepository.save(user); // 수정된 객체 저장
        } else {
            throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
        }
    }
}
