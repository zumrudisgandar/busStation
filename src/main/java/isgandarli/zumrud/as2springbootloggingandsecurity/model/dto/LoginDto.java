package isgandarli.zumrud.as2springbootloggingandsecurity.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    private String email;
    private String password;
}