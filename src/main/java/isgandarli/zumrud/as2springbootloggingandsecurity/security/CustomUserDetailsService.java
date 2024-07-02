//package isgandarli.zumrud.as2springbootloggingandsecurity.security;
//
//import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
//import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
///**
// * Custom implementation of UserDetailsService to load user details from the database.
// */
//@Service
//public class CustomUserDetailsService {
//
//    /**
//     * Provides a custom implementation of UserDetailsService.
//     */
//    @Bean
//    public UserDetailsService userDetailsService(UserRepository repo) {
//        return username -> {
//            Optional<User> res = repo.findByUsername(username);
//
//            return res.orElseThrow(() ->
//                    new UsernameNotFoundException(username + " not found")
//            );
//        };
//    }
//}
