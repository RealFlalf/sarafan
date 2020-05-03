package tsed.sarafan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tsed.sarafan.domain.User;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
