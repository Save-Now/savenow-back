package savenow.backend.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 유저 테이블
 * id, 이메일, 비밀번호, 이름, 성별, 생년월일
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 6)
    private Long birth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public User(Long id, String username, String email, String password, Long birth, Gender gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
    }

    // Setter 추가
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
