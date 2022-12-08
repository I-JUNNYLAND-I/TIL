package git.io.join.adapter.out.mail;

import git.io.join.config.Encrypt;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

public interface TextMailSend {
    void send(Naver.SenderRequest request);

    static Naver.SenderRequest toFormat(String to, String title, String text) {
        return new Naver.SenderRequest(to, title, text);
    }
    @Component
    class Naver implements TextMailSend {
        private final JavaMailSender javaMailSender;

        public Naver(JavaMailSender javaMailSender,
                     Encrypt encrypt) {
            this.javaMailSender = javaMailSender;
        }

        @Override
        public void send(SenderRequest request) {
            SimpleMailMessage mimeMessage = new SimpleMailMessage(){{
                setFrom("ADMIN@junnyland.site");
                setTo(request.to);
                setSubject(request.title);
                setText(request.text);
            }};
            javaMailSender.send(mimeMessage);
        }

        private record SenderRequest(String to, String title, String text){}
    }
}
