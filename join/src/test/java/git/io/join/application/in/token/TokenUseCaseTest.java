package git.io.join.application.in.token;

import git.io.join.application.out.token.CacheRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

class TokenUseCaseTest {

    @Autowired
    CacheRepository cacheRepository;

    @Test
    void 토큰_생성_테스트() {
        String uuid = UUID.randomUUID().toString();

        cacheRepository.save(uuid);

    }
}