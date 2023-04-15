package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Value("${UserQualPath}")
    private String path;
    @Autowired
    private UserService userService;
    @PostMapping
    public String save(@RequestBody User user){
        userService.save(user);
        return "register success";
    }

    @GetMapping("/login")
    public String login(@RequestParam("name") String loginName, @RequestParam("password") String password){

        int flag = userService.loginValidate(loginName,password);
        if (flag == 1) {
            return "login success";
        }
        else
            return "login name or password error";
    }
    @GetMapping("/getByName")
    public User getUserByLoginName(@RequestParam("name") String loginName){
            return userService.getUserByLoginName(loginName);

    }
    @GetMapping("/getById")
    public User getUserById(@RequestParam("id") Integer id){
        return userService.getUserById(id);
    }
    @PutMapping
    public String updateUser(@RequestBody User user){
        if (userService.updateUser(user) == 1)
            return "update success";
        else
            return "update fail";
    }
    @GetMapping("/getModifiedAttr")
    public User getModifiedAttr(@RequestParam String loginName){
        return userService.getModifiedAttr(loginName);
    }
    @PostMapping("/upload")
    public String uploadReceive(MultipartFile file ,@RequestParam("id") Integer id){
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        File dir = new File(path);
        String imageName = path + fileName;
        if (!dir.exists())
            dir.mkdirs();

        try {
            file.transferTo(new File(imageName));
            userService.updateUserQual(imageName,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "upload success";
    }
    @PostMapping("/insertApply")
    public String insertApply(@RequestBody Apply apply){
        if (userService.insertApplication(apply) == 1){
            return "insert apply success";
        }else
            return "insert fail";
    }
}
