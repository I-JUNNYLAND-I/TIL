package git.io.join.adapter.out.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

public interface TextMailSend {
    void send(Naver.SenderRequest request);

    static Naver.SenderRequest request(String to, String title, String text) {
        return new Naver.SenderRequest(to, title, text);
    }
    @Component
    @RequiredArgsConstructor
    class Naver implements TextMailSend {
        private final JavaMailSender javaMailSender;


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
