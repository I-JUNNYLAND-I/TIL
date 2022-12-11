package git.io.join.application.in.token;

import git.io.join.application.out.token.CacheRepository;
import git.io.join.domain.JunnyToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface TokenUseCase {

    String makeToken(String email);

    @Service
    @RequiredArgsConstructor
    class Token implements TokenUseCase {
        private final CacheRepository cacheRepository;

        @Override
        public String makeToken(String email) {

            JunnyToken junnyToken = new JunnyToken(email, UUID.randomUUID().toString());
            return cacheRepository.save(junnyToken).token();

        }
    }


}
