package savenow.backend.domain.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import savenow.backend.domain.user.User;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Category(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }

    @Override
    public boolean equals(Object c){
         if (this == c) return true;
         if (c == null || getClass()  != c.getClass()) return false;
         Category category = (Category) c;
         return Objects.equals(name, category.name);
    }

}
