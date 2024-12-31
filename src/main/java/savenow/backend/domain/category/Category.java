package savenow.backend.domain.category;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import savenow.backend.domain.userCategory.UserCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "category_tb")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<UserCategory> userCategories = new ArrayList<>();

    @Builder
    public Category(Long id, String name, Category parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public static Category rootParent() {
        return new Category();
    }

    // 연관관계 메서드
    public void addChildren(Category child) {
        this.children.add(child);
    }

    public void addUserCategory(UserCategory userCategory) {
        this.userCategories.add(userCategory);
        userCategory.setCategory(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(userCategories, category.userCategories) && Objects.equals(parent, category.parent) && Objects.equals(children, category.children);
    }

    public boolean isNotRoot() { // 자식 카테고리인지를 판별 (true 일 경우 자식임)
        return !this.equals(rootParent());
    }


}
