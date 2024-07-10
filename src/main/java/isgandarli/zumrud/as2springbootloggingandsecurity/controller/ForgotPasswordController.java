package isgandarli.zumrud.as2springbootloggingandsecurity.controller;


import isgandarli.zumrud.as2springbootloggingandsecurity.model.dto.MailBody;
import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.ForgotPassword;
import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.ForgotPasswordRepository;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
import isgandarli.zumrud.as2springbootloggingandsecurity.service.EmailService;
import isgandarli.zumrud.as2springbootloggingandsecurity.util.ChangePassword;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {

    private final UserRepository userRepository;

    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final PasswordEncoder passwordEncoder;

    public ForgotPasswordController(UserRepository userRepository, EmailService emailService, ForgotPasswordRepository forgotPasswordRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/verifyMailForm")
    public String showVerifyEmailForm(Model model) {
        model.addAttribute("email", new String());
        return "authentication/verifyMailForm";
    }

    @PostMapping("/verifyMail")
    public String verifyEmail(@RequestParam String email, Model model) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email"));

            int otp = otpGenerator();
            MailBody mailBody = MailBody.builder()
                    .to(email)
                    .text("This is the OTP for your Forgot Password request: " + otp)
                    .subject("OTP for Forgot Password Request")
                    .build();

            ForgotPassword fp = ForgotPassword.builder()
                    .otp(otp)
                    .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
                    .user(user)
                    .build();

            emailService.sendSimpleMessage(mailBody);
            forgotPasswordRepository.save(fp);
            model.addAttribute("message", "Email sent for verification!");
            return "authentication/verifyOtpForm";
        } catch (UsernameNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "authentication/verifyMailForm";
        }
    }


    @GetMapping("/verifyOtpForm")
    public String showVerifyOtpForm(Model model) {
        model.addAttribute("otp", Integer.valueOf(0));
        model.addAttribute("email", new String());
        return "authentication/verifyOtpForm";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam Integer otp, @RequestParam String email, Model model) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Please provide a valid email"));

            ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
                    .orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + email));

            if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
                forgotPasswordRepository.deleteById(fp.getFpid());
                model.addAttribute("error", "OTP has expired!");
                return "authentication/verifyOtpForm";
            }

            model.addAttribute("email", email);
            return "authentication/changePasswordForm";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "authentication/verifyOtpForm";
        }
    }


    @GetMapping("/changePasswordForm")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("changePassword", new ChangePassword("", ""));
        return "authentication/changePasswordForm";
    }

    @PostMapping("/changePassword")
    public String changePasswordHandles(@ModelAttribute ChangePassword changePassword,
                                        @RequestParam String email, Model model) {
        try {
            if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
                model.addAttribute("message", "Password and Confirm Password do not match!");
                return "authentication/changePasswordForm";
            }

            String encodedPassword = passwordEncoder.encode(changePassword.password());
            userRepository.updatePassword(email, encodedPassword);
            model.addAttribute("message", "Password has changed!");
            return "authentication/verificationResult";
        } catch (Exception e) {
            model.addAttribute("message", "An error occurred while changing the password. Please try again.");
            return "authentication/changePasswordForm";
        }
    }

    // send mail for email verification
//    @PostMapping("/verifyMail/{email}")
//    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
//
//        int otp = otpGenerator();
//        MailBody mailBody = MailBody.builder()
//                .to(email)
//                .text("This is the OTP for your Forgot Password request :" + otp)
//                .subject("OTP for Forgot Password Request")
//                .build();
//
//        ForgotPassword fp = ForgotPassword.builder()
//                .otp(otp)
//                .expirationTime(new Date(System.currentTimeMillis() + 70 * 1000))
//                .user(user)
//                .build();
//
//        emailService.sendSimpleMessage(mailBody);
//        forgotPasswordRepository.save(fp);
//
//        return ResponseEntity.ok("Email sent for verification!");
//    }
//
//    @PostMapping("/verifyOtp/{otp}/{email}")
//    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email"));
//
//        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user)
//                .orElseThrow(() -> new RuntimeException("Invalid OTP for email: " + email));
//
//        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
//            forgotPasswordRepository.deleteById(fp.getFpid());
//            return new ResponseEntity<>("OTP has expired!", HttpStatus.EXPECTATION_FAILED);
//        }
//
//        return ResponseEntity.ok("OTP verified");
//    }
//
//    @PostMapping("/changePassword/{email}")
//    public ResponseEntity<String> changePasswordHandles(@RequestBody ChangePassword changePassword,
//                                                        @PathVariable String email) {
//        if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
//            return new ResponseEntity<>("Please enter the password again!", HttpStatus.EXPECTATION_FAILED);
//        }
//
//        String encodedPassword = passwordEncoder.encode(changePassword.password());
//        userRepository.updatePassword(email, encodedPassword);
//        return ResponseEntity.ok("Password has changed!");
//    }
//
    private Integer otpGenerator() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
