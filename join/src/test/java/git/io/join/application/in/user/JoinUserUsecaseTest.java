package git.io.join.application.in.user;

import git.io.join.IntegrationTestBase;
import git.io.join.adapter.out.CacheRepository;
import git.io.join.adapter.out.FakeCacheRepository;
import git.io.join.application.in.token.TokenUseCase;
import git.io.join.application.out.FakeUserOutPort;
import git.io.join.domain.User;
import git.io.join.exception.UserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class JoinUserUsecaseTest implements IntegrationTestBase {

    JoinUserUsecase joinUserUsecase = new JoinUserUsecase.JoinUser(new FakeUserOutPort(), new FakeCacheRepository());

    @Autowired
    TokenUseCase tokenUseCase;


    @Test
    @DisplayName("유저가 신청했을 때 토큰이 없으면 에러가 발생한다.")
    void signError() {
        //given
        User user = new User(
                UUID.randomUUID().toString(),
                "mang-joo",
                "lsun606@naver.com",
                13,
                "010-5576-3376"
        );

        assertThatThrownBy(() -> joinUserUsecase.signUp(user))
                .isInstanceOf(UserException.class)
                .hasMessage("토큰이 유효 하지 않습니다.");
    }

    @Test
    @DisplayName("토큰이 있을 경우 신청 목록에 저장한다.")
    void signSave() {
        //given

        String token = tokenUseCase.makeToken();

        User user = new User(
                token,
                "mang-joo",
                "lsun606@naver.com",
                13,
                "010-5576-3376"
        );

        //when
        String userToken = joinUserUsecase.signUp(user);

        //then
        assertThat(userToken).isEqualTo(token);

    }

}