package indi.gd.pcmw.controller;

import indi.gd.pcmw.controller.util.JwtUtil;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
import indi.gd.pcmw.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/admins")
@CrossOrigin(exposedHeaders = "Authorization")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/loginValidate")
    public String adminLoginValidate(@RequestBody User user, HttpServletResponse response){
        int flag = administratorService.adminLoginValidate(user);
        if (flag == 1){
            response.setHeader("Authorization",JwtUtil.getToken(user));
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

    @DeleteMapping("/deleteApplication")
    public String deleteApplication(@RequestParam("id") Integer id){
        return administratorService.deleteApplication(id) == 1 ? "delete success" : "delete fail";
    }

}
