//package isgandarli.zumrud.as2springbootloggingandsecurity.controller;
//
//import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.AuthResponseDTO;
//import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.LoginDto;
//import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.SignupDto;
//import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
//import isgandarli.zumrud.as2springbootloggingandsecurity.security.JWTGenerator;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
///**
// * Controller class for handling authentication-related endpoints.
// */
//@Controller
//@RequestMapping("/")
//@Slf4j
//public class AuthController {
//
//    private AuthenticationManager authenticationManager;
//    private UserRepository userRepository;
//    private PasswordEncoder passwordEncoder;
//    private JWTGenerator jwtGenerator;
//
//    /**
//     * Constructor injection for dependencies.
//     */
//    @Autowired
//    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
//                          PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtGenerator = jwtGenerator;
//    }
//
//    /**
//     * Endpoint to show the login page.
//     */
//    @GetMapping("/login")
//    public String showLogin(Model model) {
//        log.info("----->INFO: User.java accessed the login page.");
//        model.addAttribute("signupDto", new SignupDto());
//        return "authentication/login";
//    }
//
//    /**
//     * Endpoint to handle user login.
//     */
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsername(),
//                        loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtGenerator.generateToken(authentication);
//        log.info("---->INFO: Generated token: {}", token);
//        log.info("---->INFO : Token is generated");
//        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
//    }
//
//    /**
//     * Endpoint to show the signup page.
//     */
//    @GetMapping("/signup")
//    public String showSignup(Model model) {
//        log.info("----->INFO: User.java accessed the signup page.");
//        model.addAttribute("signupDto", new SignupDto());
//        return "authentication/registration";
//    }
//
//    /**
//     * Endpoint to handle user registration.
//     */
//    @PostMapping("/signup")
//    public String signup(
//            @Validated @ModelAttribute SignupDto signupDto,
//            BindingResult result
//    ) {
//        try {
//            if(result.hasErrors()) {
//                log.warn("----->WARN: User.java entered invalid input");
//                return "authentication/registration";
//            }
//            log.info("----->INFO: Attempting to sign up user: {}", signupDto.getUsername());
//            userRepository.save(signupDto.toUser(passwordEncoder));
//            log.info("----->INFO: User.java signed up successfully: {}", signupDto.getUsername());
//            return "redirect:/login";
//        } catch (Exception e) {
//            log.error("----->ERROR: Failed to sign up user: {}", signupDto.getUsername(), e);
//            return "redirect:/signup?error";
//        }
//    }
//}