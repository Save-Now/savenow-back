package savenow.backend.dto.category;

import lombok.Getter;
import lombok.Setter;
import savenow.backend.domain.category.Category;

public class CategoryResDto {

    @Getter @Setter
    public static class CreateCategoryDto {
        public String name;
        public String parent;

        public CreateCategoryDto(Category category) {
            this.name =  category.getName();
            this.parent = (category.getParent() == null) ? "root" : category.getParent().getName();
        }
    }
}
