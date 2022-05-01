package com.authenticationboilerplate.authboilerplate.registration;

import com.authenticationboilerplate.authboilerplate.appUser.AppUser;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //Regix to validate email format
        return true;
    }
}
