package git.io.join.adapter.out.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenOutAdapter extends JpaRepository<TokenEnitty, Long> {
}
