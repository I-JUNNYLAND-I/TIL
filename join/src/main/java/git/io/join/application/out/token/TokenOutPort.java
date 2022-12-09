package git.io.join.application.out.token;

public interface TokenOutPort {

    Long save(String uuid);

    class TokenOutPortImpl implements TokenOutPort {

        @Override
        public Long save(String uuid) {
            return null;
        }
    }
}
