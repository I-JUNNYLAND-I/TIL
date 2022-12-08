package git.io.join.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfig {
    private final String host;
    private final String username;
    private final String password;

    public MailSenderConfig(
            Encrypt encrypt,
            @Value("${mail.host}") String host,
            @Value("${mail.username}") String username,
            @Value("${mail.password}") String password
    ) {
        this.host = encrypt.decryptAES256(host);
        this.username = encrypt.decryptAES256(username);
        this.password = encrypt.decryptAES256(password);
    }

    @Bean
    public JavaMailSender javaMailSender() {
        Properties properties = new Properties(){{
            put("mail.smtp.auth", "true");
            put("mail.smtp.starttls.enable", "true");
        }};
        return new JavaMailSenderImpl(){{
            setHost(host);
            setUsername(username);
            setPassword(password);
            setPort(587);
            setJavaMailProperties(properties);
            setDefaultEncoding("UTF-8");
        }};
    }
}
