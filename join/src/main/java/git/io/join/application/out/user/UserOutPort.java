package git.io.join.application.out.user;

import git.io.join.adapter.out.user.UserOutAdapter;
import git.io.join.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public interface UserOutPort {

    String save(User user);

    @Component
    @RequiredArgsConstructor
    class UserRepository implements UserOutPort {

        private final UserOutAdapter userOutAdapter;

        @Override
        public String save(User user) {

            return null;
        }
    }

}
