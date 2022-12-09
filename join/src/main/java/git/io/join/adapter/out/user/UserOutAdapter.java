package git.io.join.adapter.out.user;

import git.io.join.adapter.out.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOutAdapter extends JpaRepository<UserEntity, Long> {

}
