package isgandarli.zumrud.as2springbootloggingandsecurity.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "THe username field can't be blank")
    @Column(unique=true)
    private String username;

    @NotBlank(message = "The password field can't be blank")
    @Size(min = 5, message = "The password must have at least 5 characters")
    private String password;

    @NotBlank(message = "The email field can't be blank")
    @Column(unique = true)
    @Email(message = "Please enter email in proper format!")
    private String email;

//    @OneToOne(mappedBy = "user")
//    private RefreshToken refreshToken;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    private String roles; //ROLE_USER;ROLE_ADMIN -> persisted in DB

    @Transient
    private List<String> authorities = new ArrayList<>(Arrays.asList("ROLE_USER"));

    public List<GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(
                        role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User addRole(String authority) {
        this.authorities.add(authority);
        return this;
    }

    @PrePersist
    @PreUpdate
    private void saveRoles() {
        this.roles = String.join(";", this.authorities);
    }

    @PostLoad
    private void readRoles() {
        this.authorities = Arrays.stream(this.roles.split(";"))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
