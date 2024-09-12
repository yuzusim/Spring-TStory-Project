package site.metacoding.blogv3.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import site.metacoding.blogv3._core.util.ApiUtil;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final HttpSession session;
    private final UserService userService;

    //회원가입
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){
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


    @GetMapping("/user/password-reset-form")
    public String passwordResetForm() {
        return "user/passwordResetForm";
    }

    //회원정보수정
    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        UserResponse.UpdateDTO updateDTO = userService.UpdateForm(sessionUser.getId());
        request.setAttribute("user", updateDTO);

        return "user/updateForm";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout() {
        // 세션(session)을 무효화(invalidate)하는 작업을 수행
        session.invalidate();
        return "redirect:/";
    }

}
