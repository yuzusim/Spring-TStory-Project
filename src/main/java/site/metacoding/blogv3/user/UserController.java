package site.metacoding.blogv3.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import site.metacoding.blogv3._core.util.ApiUtil;
import site.metacoding.blogv3._core.util.EmailUtil;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        User sessionUser = userService.join(reqDTO);

        // 회원가입 후 바로 로그인
        session.setAttribute("sessionUser", sessionUser);
        //return ResponseEntity.ok(new ApiUtil<>(200));
        return "redirect:/";
    }

    //회원가입폼
    @GetMapping("/join-form")
    public String joinForm() {
        return "user/joinForm";
    }

    //로그인
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        User sessionUser = userService.login(reqDTO);
        session.setAttribute("sessionUser", sessionUser);
        return "redirect:/";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/loginForm";
    }

    //패스워드초기화
    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {
        return "user/passwordResetForm";
    }

    //이메일인증
    @GetMapping("/sendmail")
    public ResponseEntity<?> sendMail(EmailUtil emailUtil) {

        String emailCode = userService.sendCertificationEmail(emailUtil);
        System.out.println("emailCode = " + emailCode);
        //emailUtil.sendEmail("compilemate@gmail.com", "[Tistory 인증메일]", "되는지 테스트 하는 거예요");
        //return "메일 잘 보내졌어";
        return null;
    }

    //유저네임중복체크
    @GetMapping("/username-check")
    public ResponseEntity<?> usernameCheck(String username) {
//        System.out.println("username = " + username);
        UserEnum usernameCheck = userService.usernameCheck(username);
//        System.out.println("usernameCheck = " + usernameCheck);

        return ResponseEntity.ok(new ApiUtil<>(usernameCheck));
    }


    //회원정보수정
    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UpdateDTO updateDTO = userService.UpdateForm(sessionUser.getId());
        request.setAttribute("user", updateDTO);

        return "user/updateForm";
    }

    @PutMapping("/user/update")
    public ResponseEntity<?> update(@RequestBody UserRequest.UpdateDTO reqDTO) {
        System.out.println("비번 확인용 = " + reqDTO);
        User sessionUser = (User) session.getAttribute("sessionUser");
        userService.userUpdate(sessionUser.getId(), reqDTO);

        return ResponseEntity.ok(new ApiUtil<>(true));
    }


    //로그아웃
    @GetMapping("/logout")
    public String logout() {
        // 세션(session)을 무효화(invalidate)하는 작업을 수행
        session.invalidate();
        return "redirect:/";
    }

}
