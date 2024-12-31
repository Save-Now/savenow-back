package savenow.backend.domain.userCategory;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.domain.category.Category;
import savenow.backend.domain.user.User;

@Entity
@Table(name = "user_category_tb")
@Getter @Setter
@NoArgsConstructor
public class UserCategory {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public UserCategory(Long id, User user, Category category) {
        this.id = id;
        this.user = user;
        this.category = category;
    }

    //생성 메서드
    public static UserCategory createUserCategory(User user,Category category) {
        UserCategory userCategory = new UserCategory();
        userCategory.setUser(user);
        userCategory.setCategory(category);

        return userCategory;
    }
}
