package site.metacoding.blogv3.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.metacoding.blogv3.category.CategoryResponse;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p ORDER BY rand() limit 8")
    List<Post> findAllRandom();

    // 게시글 리스트
    @Query("select new site.metacoding.blogv3.post.PostResponse$ListDTO(p.id, p.thumbnailFile, p.title, p.content, p.createdAt)" +
            "from Post p where p.user.id = :sessionUserId order by p.id desc")
    List<PostResponse.ListDTO> findByPostList(Integer sessionUserId);


}
