package git.io.join.adapter.out.TalkSender;

import git.io.join.adapter.out.mail.TextMailSend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static git.io.join.adapter.out.mail.TextMailSend.request;

public interface SendAlarmTalk {
    void send(String message);

    @Service
    @RequiredArgsConstructor
    class Kakao implements SendAlarmTalk {
        private final WebClient webClient;

        private final String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";
        @Override
        public void send(String message) {
        }
    }
}
