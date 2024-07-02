package isgandarli.zumrud.as2springbootloggingandsecurity.model.dto;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class SignupDto {
    /** Validation annotation to ensure that the username is not blank */
    @NotBlank(message = "The username is required")
    private String username;

    /** Validation annotation to ensure that the password is not blank */
    @NotBlank(message = "The password is required")
    private String password;

    /** Validation annotation to ensure that the email is not blank */
    @NotBlank(message = "The email is required")
    private String email;

    public User toUser(PasswordEncoder encoder) {
        return new User(username, encoder.encode(password), email);
    }

}
