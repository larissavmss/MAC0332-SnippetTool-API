package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.service.Login;
import br.com.usp.mac0332.snippettool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;



    @GetMapping("/login")
    public String login(Model model, @RequestParam(name="error", required = false) String error) {
        model.addAttribute("error", error);
        return "auth/login";
    }

    @PostMapping("/login-test")
    public String login(@RequestBody Login login) {
        userService.loadUserByUsername(login);
        return "auth/login";
    }

}
