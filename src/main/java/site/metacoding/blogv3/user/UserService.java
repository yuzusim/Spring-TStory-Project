package site.metacoding.blogv3.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.blogv3._core.exception.ApiException400;
import site.metacoding.blogv3._core.exception.ApiException404;
import site.metacoding.blogv3._core.exception.Exception401;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepo;
    //private final EmailUtil emailUtil;

    //로그인(조회라 트랜젝션 안 붙여도 됨!)
    public User login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJPARepo.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 혹은 비밀번호를 확인해주세요.")); // orElseThrow 값이 null이면 이라는 뜻
        return sessionUser; // 세션에 저장
    }

    //회원가입
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO){
        // 유저네임 중복검사(서비스 체크) - DB 연결이 필요함
        Optional<User> userOP = userJPARepo.findByUsername(reqDTO.getUsername());

        // isPresent가 있으면 비정상
        if (userOP.isPresent()) { // 유저 중복
            throw new ApiException400("중복된 아이디입니다."); // 예외처리
        }

        // 아닌 경우 정상적으로 저장
        User user = userJPARepo.save(reqDTO.toEntity());
        return user; 
    }

    //회원정보수정(값 뿌리기 조회)
    public UserResponse.UpdateDTO UpdateForm(Integer sessionUserId) {
        User user = userJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new ApiException404("회원 정보가 존재하지 않습니다."));

        return new UserResponse.UpdateDTO(user.getUsername(), user.getEmail());

    }


}
