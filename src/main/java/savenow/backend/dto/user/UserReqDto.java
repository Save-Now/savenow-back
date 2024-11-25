package savenow.backend.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import savenow.backend.domain.Gender;
import savenow.backend.domain.User;


public class UserReqDto {
    @Getter
    @Setter
    // 조건은 임시로 정해놓은 상태
    public static class JoinReqDto {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
        @NotEmpty
        private String email;
        @NotNull
        private Long birth;
        private Gender gender;

        public User toEntity(BCryptPasswordEncoder passwordEncoder) {
            return User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(password))
                    .email(email)
                    .birth(birth)
                    .gender(gender)
                    .build();
        }

    }
}
