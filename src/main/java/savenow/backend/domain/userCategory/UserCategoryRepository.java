package savenow.backend.domain.userCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import savenow.backend.domain.category.Category;
import savenow.backend.domain.user.User;

import java.util.List;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    List<UserCategory> findByUser(User user);

    List<UserCategory> findByCategory(Category category);
}
