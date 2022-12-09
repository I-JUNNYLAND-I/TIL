package git.io.join.application.in.user;

import git.io.join.adapter.out.CacheRepository;
import git.io.join.application.out.user.UserOutPort;
import git.io.join.domain.User;
import git.io.join.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

public interface JoinUserUsecase {

    String signUp(User user);


    @Service
    @RequiredArgsConstructor
    class JoinUser implements JoinUserUsecase {

        private final UserOutPort userOutPort;
        private final CacheRepository cacheRepository;

        @Override
        public String signUp(User user) {

            if (cacheRepository.isNotHave(user.token())) {
                throw new UserException("토큰이 유효 하지 않습니다.");
            }

            return userOutPort.save(user);
        }
    }

}
