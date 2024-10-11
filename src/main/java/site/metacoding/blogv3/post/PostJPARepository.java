package site.metacoding.blogv3.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.metacoding.blogv3.category.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

    // 게시글 상세보기
    @Query("select new site.metacoding.blogv3.post.PostResponse$DetailDTO(p.id, p.title, p.content, p.user.id, p.user.username, p.createdAt)" +
            "from Post p where p.id = :postId")
    Optional<PostResponse.DetailDTO> findByPostId(Integer postId);

//    @Query("select b from Post b join fetch b.user u where b.id = :id")
//    Optional<Post> findByIdJoinUser(@Param("id") int id);

    @Query("SELECT p FROM Post p JOIN FETCH p.user WHERE p.id = :postId")
    Optional<Post> findByIdWithUser(@Param("postId") Integer postId);



    // 게시글 리스트
    @Query("select new site.metacoding.blogv3.post.PostResponse$ListDTO(p.id, p.thumbnailFile, p.title, p.content, p.createdAt)" +
            "from Post p where p.user.id = :sessionUserId order by p.id desc")
    List<PostResponse.ListDTO> findByPostList(Integer sessionUserId);

}
