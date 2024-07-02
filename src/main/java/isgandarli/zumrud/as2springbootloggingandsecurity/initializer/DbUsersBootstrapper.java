package isgandarli.zumrud.as2springbootloggingandsecurity.initializer;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Initializer class for creating default instances of users.
 */
@Component
public class DbUsersBootstrapper {
    @Bean
    public ApplicationRunner init(UserRepository userRepo, PasswordEncoder encoder) {
        return (args) -> {
            User adminUser = new User("admin",
                    encoder.encode("admin"), "admin_user@ada.edu.az");
            userRepo.save(adminUser.addRole("ROLE_ADMIN"));

            userRepo.save(
                    new User("zumisg",
                            encoder.encode("12345"), "zisgandarli15645@ada.edu.az"));
        };
    }
}