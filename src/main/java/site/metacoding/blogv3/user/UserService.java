package site.metacoding.blogv3.user;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.metacoding.blogv3._core.exception.ApiException400;
import site.metacoding.blogv3._core.exception.ApiException404;
import site.metacoding.blogv3._core.exception.Exception401;
import site.metacoding.blogv3._core.util.EmailUtil;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserJPARepository userJPARepo;
    //private final JavaMailSender sender;
    private final EmailUtil emailUtil;

    //로그인(조회라 트랜젝션 안 붙여도 됨!)
    public User login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userJPARepo.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("아이디 혹은 비밀번호를 확인해주세요.")); // orElseThrow 값이 null이면 이라는 뜻
        return sessionUser; // 세션에 저장
    }

    //회원가입
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO) {
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

    //회원저보수정(비밀번호변경)
    @Transactional
    public void userUpdate(Integer sessionUserId, UserRequest.UpdateDTO reqDTO) {
        //먼저 조회
        User user = userJPARepo.findById(sessionUserId)
                //.orElseThrow(() -> new ApiException404("회원 정보가 존재하지 않습니다."));
//                .orElseThrow(() -> new RuntimeException());

                .orElseThrow(() -> new ApiException404("존재하지 않는 회원입니다."));
        System.out.println("user = " + user);

        if (reqDTO.getNewPassword().equals(user.getPassword())) {
            throw new RuntimeException("동일한 비밀번호로는 변경할 수 없습니다.");
        }

        if (!reqDTO.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("기존 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(reqDTO.getNewPassword());
    }

    // username 중복 검색
    public UserEnum usernameCheck(String username) {
        // 주어진 username으로 사용자 정보를 조회
        Optional<User> userOP = userJPARepo.findByUsername(username);

        //System.out.println("username = " + username);
        //System.out.println("userOP = " + userOP);

        // UserEnum 타입의 변수를 선언
        UserEnum userStatus;
        
        // 조회된 사용자 정보가 있을 경우
        if (userOP.isPresent()) {
            userStatus = UserEnum.USER_EXIST; // 사용자 존재 상태를 설정(유저 중복)
            System.out.println("userStatus = " + userStatus);
            return userStatus; // 설정된 상태를 반환

        } else {
            // 조회된 사용자 정보가 없을 경우
            userStatus = UserEnum.USER_NO_EXIST; // 사용자 미존재 상태를 설정(유저 네임 사용 가능)
            System.out.println("userStatus = " + userStatus);
        }
        return userStatus; // USER_EXIST 또는 USER_NO_EXIST 반환

    }

    //이메일인증
//    public String mailCheck(String email){
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        String htmlContent = getCertificationMessage(body);
//
//        try {
//            // 어디로 메일 보낼거냐
//            helper.setTo(toAddress);
//            // 제목이 무엇이냐
//            helper.setSubject(subject);
//            // true로 하면 html 디자인 되어있는 콘텐트를 넣어준다
//            helper.setText(htmlContent, true);
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        // 내가 설정한 이메일로 날아감
//        sender.send(message);
//
//
//        return null;
//    }

    public CertificationService(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    public String sendCertificationEmail(String emailAddress) {
        // 1. 인증 코드 생성
        String certificationNumber = generateCertificationNumber();

        // 2. 이메일 전송
        String subject = "Your Certification Code";
        String body = certificationNumber;
        emailUtil.sendEmail(emailAddress, subject, body);

        // 3. 생성된 인증 코드를 반환 (DB나 캐시에 저장할 수 있음)
        return certificationNumber;
    }

    public String generateCertificationNumber() {
        Random random = new Random();
        int certificationNumber = random.nextInt(900000) + 100000; // 6자리 인증번호 생성
        return String.valueOf(certificationNumber);
    }




}



