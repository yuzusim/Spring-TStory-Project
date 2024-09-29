package site.metacoding.blogv3.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

    @Query("select p from Post p ORDER BY rand() limit 8")
    List<Post> findAllRandom();
}
