package site.metacoding.blogv3._core.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EmailUtil {

    private final JavaMailSender sender;

    // 메일 전송하는 메소드
    public void sendEmail(String toAddress, String subject, String body) {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String htmlContent = getCertificationMessage(body);

        try {
            // 어디로 메일 보낼거냐
            helper.setTo(toAddress);
            // 제목이 무엇이냐
            helper.setSubject(subject);
            // true로 하면 html 디자인 되어있는 콘텐트를 넣어준다
            helper.setText(htmlContent, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

//        // 내가 설정한 이메일로 날아감
//        sender.send(message);

        try {
            sender.send(message);
        } catch (MailException e) {
            e.printStackTrace(); // 실패 시 처리
        }

    }


    private String getCertificationMessage(String body) {

        String certificationMessage = "";
        certificationMessage += "<h1 style='text-align: center;'>[Jistory 인증메일]</h1>";
        certificationMessage += "<h4 style='text-align: center;'>아래 숫자를 입력하고 회원가입을 완료해 주세요!</h4>";
        certificationMessage += "<h3 style='text-align: center;'>" +
                "인증코드 : <strong style='font-size: 32px; letter-spacing: 8px;'>" + body + "</strong></h3>";

        return certificationMessage;
    }
}
