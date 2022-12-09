package git.io.join.application.out.token;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeTokenOutPort implements TokenOutPort {



    @Override
    public Long save(String uuid) {
        return null;
    }
}