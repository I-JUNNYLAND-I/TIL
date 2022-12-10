package git.io.join.application.out.token;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

public interface CacheRepository {
    Boolean isNotHave(String key);
    void save(String key);

    @Slf4j
    @Repository
    @RequiredArgsConstructor
    class CacheValid implements CacheRepository {
        private final CacheManager cacheManager;

        @Override
        public Boolean isNotHave(String key) {
            return cacheManager.getCache("cache").get(key) == null;
        }

        @Override
        @Cacheable(value = "cache", key = "#key")
        public void save(String key) {
            log.info("Token Saved ::: {}", key);
        }
    }
}
