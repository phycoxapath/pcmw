package indi.gd.pcmw.controller;

import indi.gd.pcmw.controller.util.JwtUtil;
import indi.gd.pcmw.domain.Commodity;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
import indi.gd.pcmw.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admins")
@CrossOrigin(exposedHeaders = "Authorization")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Value("${commodityImgTempPath}")
    private String path;
    @PostMapping("/loginValidate")
    public String adminLoginValidate(@RequestBody User user, HttpServletResponse response){
        int flag = administratorService.adminLoginValidate(user);
        if (flag == 1){
            response.setHeader("Authorization",JwtUtil.getToken(user.getLoginName(), "admin"));
        }
        return flag == 1 ? "login success" : "login fail";
    }

    @GetMapping("/getAllHospitals")
    public List<Hospital> getAllHospitals(){
        return administratorService.getAllHospitals();
    }

    @GetMapping("/getApplyByType")
    public List<ApplyDTO> getApplyByType(@RequestParam("id") Integer id, @RequestParam("type") String type){
        return administratorService.getApplyByType(id, type);
    }

    @PutMapping("/updateHospQual")
    public String updateHospQual(@RequestParam("hospId") Integer hospId, @RequestParam("qualification") boolean qualification){
        return administratorService.updateHospQual(hospId, qualification) == 1 ? "update success" : "update fail";
    }
    @PutMapping("/updateUserQual")
    public String updateUserQual(@RequestParam("userId") Integer userId, @RequestParam("qualification") boolean qualification){
        return administratorService.updateUserQual(userId, qualification) == 1 ? "update success" : "update fail";
    }

    @DeleteMapping("/deleteApplication")
    public String deleteApplication(@RequestParam("id") Integer id){
        return administratorService.deleteApplication(id) == 1 ? "delete success" : "delete fail";
    }

    @GetMapping("/getAllUsers")
    List<User> getAllUsers(){
        return administratorService.getAllUsers();
    }
    @DeleteMapping("/deleteUserById")
    public String deleteUserById(@RequestParam("id") Integer id){
        return administratorService.deleteUserById(id) == 1 ? "delete success" : "delete fail";
    }

    @PostMapping("/upload")
    public String uploadReceive(MultipartFile file){
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        File dir = new File(path);
        String imageName = path + fileName;

        if (!dir.exists())
            dir.mkdirs();

        try {
            file.transferTo(new File(imageName));
            return imageName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/removeImg")
    public void removeImg(@RequestParam("fileName") String fileName){
        System.out.println(fileName);
        File f = new File(fileName);
        if (!f.isDirectory() && f.exists()){
            f.delete();
        }
    }

    @PostMapping("/saveCommodity")
    public String saveCommodity(@RequestBody Commodity commodity){
        return administratorService.saveCommodity(commodity) == 1 ? "save success" : "save fail";
    }
}
