package git.io.join.application.out.token;

import git.io.join.IntegrationTestBase;
import git.io.join.application.out.token.CacheRepository;
import git.io.join.domain.JunnyToken;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.*;


class CacheRepositoryTest implements IntegrationTestBase {

    @Autowired
    CacheRepository cacheRepository;

    @Test
    void save() {
        JunnyToken test = new JunnyToken("abc@naver.com", "test");
        JunnyToken saveJunnyToken = cacheRepository.save(test);
        assertThat(saveJunnyToken.token()).isEqualTo(test.token());
        assertThat(saveJunnyToken.email()).isEqualTo(test.email());

    }

    @Test
    void isHave() {
        JunnyToken junnyToken = new JunnyToken("abc@naver.com", "test");
        cacheRepository.save(junnyToken);
        Boolean test = cacheRepository.isNotHave("test");

        assertThat(test).isFalse();
    }


}