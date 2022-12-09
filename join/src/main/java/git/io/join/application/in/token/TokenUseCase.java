package git.io.join.application.in.token;

import git.io.join.adapter.out.CacheRepository;
import git.io.join.adapter.out.token.TokenOutAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface TokenUseCase {

    String makeToken();

    @Service
    @RequiredArgsConstructor
    class Token implements TokenUseCase {
        private final CacheRepository cacheRepository;

        @Override
        public String makeToken() {

            String uuid = UUID.randomUUID().toString();

            cacheRepository.save(uuid);

            return uuid;
        }
    }


}
