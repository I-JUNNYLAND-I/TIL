package git.io.join.adapter.out;

import git.io.join.application.out.token.CacheRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeCacheRepository implements CacheRepository {

    Map<String, String> map = new HashMap<>();

    @Override

    public Boolean isNotHave(String key) {

        try {
            map.get(key);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public void save(String key) {
        map.put(key, key);
    }
}
