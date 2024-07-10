package isgandarli.zumrud.as2springbootloggingandsecurity.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class SignupDto {

    private String name;
    private String email;
    private String password;

}
