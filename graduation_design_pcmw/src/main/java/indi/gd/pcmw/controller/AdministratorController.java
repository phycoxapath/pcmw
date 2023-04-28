package indi.gd.pcmw.controller;

import indi.gd.pcmw.controller.util.JwtUtil;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admins")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/loginValidate")
    public String adminLoginValidate(@RequestBody User user, HttpServletResponse response){
        int flag = administratorService.adminLoginValidate(user);
        if (flag == 1){
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            response.setHeader("Authorization",JwtUtil.getToken(user));
        }
        return flag == 1 ? "login success" : "login fail";
    }
}
