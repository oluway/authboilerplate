package com.authenticationboilerplate.authboilerplate.registration;

import com.authenticationboilerplate.authboilerplate.appUser.AppUser;
import com.authenticationboilerplate.authboilerplate.appUser.AppUserRole;
import com.authenticationboilerplate.authboilerplate.appUser.AppUserService;
import com.authenticationboilerplate.authboilerplate.registration.token.ConfirmationToken;
import com.authenticationboilerplate.authboilerplate.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegisterationService {
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserService appUserService;
    private final EmailValidatorService emailValidatorService;


    public String register(RegistrationRequest request) {
       Boolean isValidEmail = emailValidatorService.test(request.getEmail());
       if(!isValidEmail){
           throw new IllegalStateException("Email address " + request.getEmail() + " is not valid");
       }

        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = (ConfirmationToken) confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }
}
