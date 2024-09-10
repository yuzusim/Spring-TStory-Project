package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.blogv3._core.exception.ApiException400;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepo;
    //private final EmailUtil emailUtil;

    @Transactional // userJPARepository 가 들고 있으면 안돼!! 한번에 여러개를 할 수 있으니까 서비스에서 통으로 관리해야 함
    public User join(UserRequest.JoinDTO reqDTO){

        // 1. 유효성 검사(컨트롤러의 책임)
        // save 하다가 날 수있는 오류의 종류가 엄청나게 많다
        // 트라이캐치로 퉁치면 어디서 오류가 나는지 알 수 없다.

        // 2. 유저네임 중복검사(서비스 체크) - DB 연결이 필요함
        Optional<User> userOP = userJPARepo.findByUsername(reqDTO.getUsername());


        // isPresent가 있으면 비정상
        if (userOP.isPresent()) { // 유저 중복
            throw new ApiException400("중복된 아이디입니다."); // 예외처리
        }

        // 아닌 경우 정상적으로 저장
        User user = userJPARepo.save(reqDTO.toEntity());
        return user;

    }

}
