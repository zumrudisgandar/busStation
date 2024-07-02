package isgandarli.zumrud.as2springbootloggingandsecurity.repo;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository interface for managing User entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
