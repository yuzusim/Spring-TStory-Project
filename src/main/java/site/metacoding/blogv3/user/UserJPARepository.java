package site.metacoding.blogv3.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJPARepository extends JpaRepository<User, Integer> {

    // findByUsernameAndPassword 로 작성하면 jpa query method가 query creation을 발동시킨다.
    // 복잡한 쿼리는 레포 하나 더 만들어서 사용한다. (엔티티명 쿼리 레포)
    // 간단한 쿼리 작성하기(join 가능)
    // @Query("select u from User u where u.username = :username and u.password = :password")
    // 추상 메드 생성
    // 로그인용 쿼리메소드
    Optional<User> findByUsernameAndPassword(@Param("username") String username, @Param("password")  String password);

    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsername(@Param("username") String username);

}
