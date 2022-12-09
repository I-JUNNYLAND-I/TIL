package git.io.join.domain;

public record User(
        String token,
        String branch,
        String email,
        Integer period,
        String phone
) {
}
