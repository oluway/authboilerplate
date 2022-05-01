package com.authenticationboilerplate.authboilerplate.registration;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.spi.RegisterableService;

@RestController
@RequestMapping("api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
//    @Autowired
    private RegisterationService registerationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
       return registerationService.register(request);
    }
}
