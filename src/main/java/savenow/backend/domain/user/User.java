package savenow.backend.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 유저 테이블
 * id, 이메일, 비밀번호, 이름, 성별
 * 구현 완료 확인 후 프로필 사진 추가 예정
 */

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_tb")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username; // 유저 이름 (닉네임)

    @Column(unique = true,nullable = false)
    private String email; // 유저 이메일

    @Column(nullable = false)
    private String password; // 유저 비밀번호

    @Column(length = 6)
    private Long birth; // 유저 생년월일 (020202 형식)

    @Enumerated
    private Gender gender; // 유저 성별

    @Builder
    public User(Long id, String username, String email, String password, Long birth, Gender gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
    }
}

