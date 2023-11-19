package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.service.Login;
import br.com.usp.mac0332.snippettool.service.MyUserDetails;
import br.com.usp.mac0332.snippettool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/findByName")
    public User findByName(@RequestParam("name") String name){
        return userService.findByUsername(name);
    }

    @GetMapping("/getAll")
    public List<User> getAll(){

        return userService.findAll();
    }

    @DeleteMapping
    public void deleteThisUser(@AuthenticationPrincipal UserDetails userDetails){
        userService.delete(((MyUserDetails) userDetails).user);
    }

    @PutMapping
    public void editUser(@RequestBody Login login, @AuthenticationPrincipal UserDetails userDetails){
        userService.editUserFromLogin(login, ((MyUserDetails) userDetails).user);
    }


}
