package git.io.join.application.out.token;

import git.io.join.domain.JunnyToken;

import java.util.HashMap;
import java.util.Map;

public class FakeCacheRepository implements CacheRepository {

    private static final Map<String, JunnyToken> map = new HashMap<>();

    @Override
    public Boolean isNotHave(String junnyToken) {
        return map.get(junnyToken) == null;
    }

    @Override
    public JunnyToken save(JunnyToken junnyToken) {
        map.put(junnyToken.token(), junnyToken);
        return junnyToken;
    }

}
