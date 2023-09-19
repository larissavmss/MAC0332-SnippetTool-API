package br.com.usp.mac0332.snippettool.controller;

import br.com.usp.mac0332.snippettool.model.Folder;
import br.com.usp.mac0332.snippettool.model.User;
import br.com.usp.mac0332.snippettool.service.FolderService;
import br.com.usp.mac0332.snippettool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/add")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping("/findByName")
    public User findByName(@RequestParam("name") String name){
        return userService.findByUsername(name);
    }
}
