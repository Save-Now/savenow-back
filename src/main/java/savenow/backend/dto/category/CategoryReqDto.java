package savenow.backend.dto.category;

import lombok.Getter;
import lombok.Setter;
import savenow.backend.domain.category.Category;

import java.util.Objects;

public class CategoryReqDto {

    @Getter @Setter
    public static class CreateCategoryReqDto {
        private String name;
        private String parent;

        public Category toEntity(Category realParent) {
            Category parent = !Objects.equals(realParent,Category.rootParent()) ? realParent : null;

            return Category.builder()
                    .parent(parent)
                    .name(name)
                    .build();
        }

        public Boolean hasParent() {
            return parent != null;
        }
    }

}
