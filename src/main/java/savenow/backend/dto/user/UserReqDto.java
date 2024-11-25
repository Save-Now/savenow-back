package savenow.backend.dto.user;

import jakarta.validation.constraints.*;
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
        // 회원 이름 : 한글, 영어 숫자 가능, 12자 이내
        @NotBlank
        @Pattern(regexp = "^[a-zA-Z가-힣0-9]{2,12}$" , message = "한글/영문/숫자 2~12자 이내로 작성해 주세요")
        private String username;

        @NotBlank
        @Pattern(regexp = "^[a-zA-z0-9]{4,12}$", message = "한글/영문/숫자 4~12자 이내로 작성해주세요")
        private String email;

        @NotBlank
        @Size(min = 4, max = 12, message = "4~12자 이내로 작성해주세요")
        private String password;

        @NotNull
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
