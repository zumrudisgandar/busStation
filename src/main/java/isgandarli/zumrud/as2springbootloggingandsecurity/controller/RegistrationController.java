//package isgandarli.zumrud.as2springbootloggingandsecurity.controller;
//
//
//import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.SignupDto;
//import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//
//@Controller
//@RequestMapping("/signup")
//@Slf4j
//public class RegistrationController {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    /**
//     * Displays the signup page.
//     *
//     * @param model the model object to be populated with data for the view
//     * @return the view name for the signup page
//     */
//    @GetMapping
//    public String showSignup(Model model) {
//        log.info("----->INFO: User accessed the signup page.");
//        model.addAttribute("signupDto", new SignupDto());
//        return "authentication/registration";
//    }
//
//
//    /**
//     * Processes user signup form submission.
//     *
//     * @param signupDto the DTO object containing user signup data
//     * @param result    the binding result for validation errors
//     * @return the view name for redirecting the user after signup
//     */
//    @PostMapping
//    public String signup(
//            @Validated @ModelAttribute SignupDto signupDto,
//            BindingResult result
//    ) {
//        try {
//            if(result.hasErrors()) {
//                log.warn("----->WARN: User entered invalid input");
//                return "authentication/registration";
//            }
//            log.info("----->INFO: Attempting to sign up user: {}", signupDto.getUsername());
//            userRepo.save(signupDto.toUser(passwordEncoder));
//            log.info("----->INFO: User signed up successfully: {}", signupDto.getUsername());
//            return "redirect:/login";
//        } catch (Exception e) {
//            log.error("----->ERROR: Failed to sign up user: {}", signupDto.getUsername(), e);
//            return "redirect:/signup?error";
//        }
//    }
//}