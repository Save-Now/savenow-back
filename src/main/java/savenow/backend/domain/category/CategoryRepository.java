package savenow.backend.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import savenow.backend.domain.userCategory.UserCategory;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByName(String name);


    @Query("SELECT c FROM Category c JOIN c.userCategories uc WHERE uc = :userCategory")

    Optional<Category> findByUserCategory(@Param("userCategory") UserCategory userCategory);

}
