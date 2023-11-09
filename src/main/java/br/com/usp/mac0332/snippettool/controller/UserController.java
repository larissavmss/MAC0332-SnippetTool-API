package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
