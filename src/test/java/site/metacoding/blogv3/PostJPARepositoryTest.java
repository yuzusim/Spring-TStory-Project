package site.metacoding.blogv3;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.metacoding.blogv3.post.PostJPARepository;

@DataJpaTest
public class PostJPARepositoryTest {

    @Autowired
    private PostJPARepository postJPARepo;

    @Autowired
    private EntityManager em;

    //deleteById
    @Test
    public void deleteById_test() {
        //given
        int id = 1;

        //when
        postJPARepo.deleteById(postId);
        em.flush();

        //then
    }

}
