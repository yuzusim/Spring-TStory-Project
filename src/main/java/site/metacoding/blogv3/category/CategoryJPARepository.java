package site.metacoding.blogv3.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<Category, Integer> {


    @Query("select c from Category c where c.categoryName = :categoryName and c.user.id = :sessionUserId")
    Optional<Category> findByCategoryNameAndUserId (String categoryName, Integer sessionUserId);




}
