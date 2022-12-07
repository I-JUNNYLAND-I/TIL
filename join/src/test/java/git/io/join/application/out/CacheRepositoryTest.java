package git.io.join.application.out;

import git.io.join.IntegrationTestBase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class CacheRepositoryTest implements IntegrationTestBase {

    @Autowired
    CacheRepository cacheRepository;

    @Test
    void save() {
        cacheRepository.save("test");
    }

    @Test
    void isHave() {
        cacheRepository.save("test");
        Boolean test = cacheRepository.isNotHave("test");

        Assertions.assertThat(test).isFalse();
    }


}