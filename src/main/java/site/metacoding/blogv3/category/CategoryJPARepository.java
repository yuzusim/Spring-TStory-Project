package site.metacoding.blogv3.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.metacoding.blogv3.post.PostResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryJPARepository extends JpaRepository<Category, Integer> {

    // 카테고리 조회
    @Query("select c from Category c where c.categoryName = :categoryName and c.user.id = :sessionUserId")
    Optional<Category> findByCategoryNameAndUserId(String categoryName, Integer sessionUserId);



    // 카테고리 리스트 조회
    @Query("select new site.metacoding.blogv3.category.CategoryResponse$CategoryNameDTO(c.id, c.categoryName) " +
            "from Category c where c.user.id = :sessionUser order by c.categoryName")
    List<CategoryResponse.CategoryNameDTO> findByUserId(@Param("sessionUser") Integer sessionUser);



}
