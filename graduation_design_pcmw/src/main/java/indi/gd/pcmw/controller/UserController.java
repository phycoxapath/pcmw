package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping
    public String save(@RequestBody User user){
        userService.save(user);
        return "register success";
    }

    @GetMapping("/{loginName}/{password}")
    public String login(@PathVariable("loginName") String loginName, @PathVariable("password") String password){

        int flag = userService.loginValidate(loginName,password);
        if (flag == 1) {
            return "login success";
        }
        else
            return "login name or password error";
    }
    @GetMapping("/{loginName}")
    public User getUserByLoginName(@PathVariable String loginName){
        return userService.getUserByLoginName(loginName);
    }
}
