package git.io.join.adapter.out.mail;

import git.io.join.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static git.io.join.adapter.out.mail.TextMailSend.toFormat;
import static org.junit.jupiter.api.Assertions.*;

class TextMailSendTest implements IntegrationTestBase {

    @Autowired
    TextMailSend textMailSend;
    @Test
    void send() {
        textMailSend.send(toFormat("chbe5082@naver.com","test","test"));
    }
}