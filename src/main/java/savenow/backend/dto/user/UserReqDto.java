package savenow.backend.dto.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import savenow.backend.domain.user.Gender;
import savenow.backend.domain.user.User;


public class UserReqDto {
    @Getter
    @Setter
    // 조건은 임시로 정해놓은 상태
    public static class JoinReqDto {
        // 회원 이름 : 한글, 영어 숫자 가능, 12자 이내
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z가-힣0-9]{2,20}$" , message = "한글/영문/숫자 2~20자 이내로 작성해 주세요")
        private String username;

        @NotBlank
        @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",
        message = "이메일 형식으로 작성해주세요")
        private String email;

        @NotBlank
        @Size(min = 8 ,message = "8자 이상으로 작성해주세요")
        private String password;

        @NotNull(message = "생년월일을 입력해주세요")
        private Long birth;

        @NotNull(message = "성별을 선택해주세요")
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
