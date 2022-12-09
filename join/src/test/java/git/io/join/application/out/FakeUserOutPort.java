package git.io.join.application.out;

import git.io.join.application.out.user.UserOutPort;
import git.io.join.domain.User;

import java.util.HashMap;
import java.util.Map;

public class FakeUserOutPort implements UserOutPort {

    Map<String, User> userMap = new HashMap<>();

    @Override
    public String save(User user) {
        userMap.put(user.token(), user);
        return userMap.get(user.token()).token();
    }
}