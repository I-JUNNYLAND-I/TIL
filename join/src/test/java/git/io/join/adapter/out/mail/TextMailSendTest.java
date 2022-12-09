package git.io.join.adapter.out.mail;

import git.io.join.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static git.io.join.adapter.out.mail.TextMailSend.request;

class TextMailSendTest implements IntegrationTestBase {

    @Autowired
    TextMailSend textMailSend;
    @Test
    void send() {
        textMailSend.send(request("chbe5082@naver.com","test","test"));
    }
}