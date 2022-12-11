package git.io.join.application.out.token;

import git.io.join.domain.JunnyToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

public interface CacheRepository {
    Boolean isNotHave(String junnyToken);

    JunnyToken save(JunnyToken junnyToken);

    @Slf4j
    @Repository
    @RequiredArgsConstructor
    class CacheValid implements CacheRepository {
        private final CacheManager cacheManager;

        @Override
        public Boolean isNotHave(String junnyToken) {
            return cacheManager.getCache("cache").get(junnyToken) == null;
        }

        @Override
        @Cacheable(value = "cache", key = "#junnyToken.token")
        public JunnyToken save(JunnyToken junnyToken) {
            log.info("JunnyToken Saved ::: {}", junnyToken.token());
            return junnyToken;
        }
    }
}
