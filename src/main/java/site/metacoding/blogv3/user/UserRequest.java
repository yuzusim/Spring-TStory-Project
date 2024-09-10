package site.metacoding.blogv3.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class UserRequest {

    //회원정보수정
    @Data
    public static class UpdateDTO {
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        @Size(min = 4, max = 20, message = "비밀번호는 4글자 이상, 20글자 이하여야 합니다.")
        private String password;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        @Size(min = 4, max = 20, message = "비밀번호는 4글자 이상, 20글자 이하여야 합니다.")
        private String newPassword;
    }

    //로그인
    @Data
    public static class LoginDTO{
        @NotEmpty(message = "아이디를 입력해주세요.")
        private String username;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    //회원가입
    @Data
    public static class JoinDTO{
        @NotEmpty(message = "아이디를 입력해주세요.")
        @Size(min = 3, max = 10, message = "아이디는 3글자 이상, 10글자 이하여야 합니다.")
        private  String username;
        @NotEmpty(message = "비밀번호를 입력해주세요.")
        @Size(min = 4, max = 20, message = "비밀번호는 4글자 이상, 20글자 이하여야 합니다.")
        private String password;
        @NotEmpty(message = "이메일을 입력해주세요.")
        @Email(message = "유효한 이메일 주소를 입력해주세요.")
        private String email;
        private Boolean isEmailConfirmed;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }
}
