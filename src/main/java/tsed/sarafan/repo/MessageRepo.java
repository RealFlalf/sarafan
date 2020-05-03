package tsed.sarafan.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tsed.sarafan.domain.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
