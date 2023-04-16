package com.ecommerce.user.controllers;

import com.ecommerce.user.models.User;
import com.ecommerce.user.payload.response.MessageResponse;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.EmailService;
import com.ecommerce.user.service.ForgotPasswordService;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/reset-password")
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    private final EmailService emailService;

    private final UserRepository userRepository;

    public ForgotPasswordController(ForgotPasswordService forgotPasswordService, EmailService emailService, UserRepository userRepository) {
        this.forgotPasswordService = forgotPasswordService;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> processForgotPassword(@RequestParam(value = "email") String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Could not find any User with the email " + email));
        }

        String token = RandomString.make(30);

        forgotPasswordService.updateResetPasswordToken(token, email);
        emailService.sendEmail(email, token);

        return ResponseEntity
                .ok(new MessageResponse("Token successfully sent to email!"));
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitForgotPassword(@RequestParam(value = "newPassword") String newPassword,
                                                  @RequestParam(value = "confirmNewPassword") String confirmNewPassword,
                                                  @RequestParam(value = "token") String token) {
        if (!newPassword.equals(confirmNewPassword)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: New Password not match!"));
        }

        User user = forgotPasswordService.getByResetPasswordToken(token);
        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Invalid Token!"));
        }

        forgotPasswordService.updatePassword(user, newPassword);
        return ResponseEntity
                .ok(new MessageResponse("You have successfully changed your password."));
    }
}
