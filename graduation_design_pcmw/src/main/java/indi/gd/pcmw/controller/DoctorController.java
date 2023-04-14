package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.DoctorDTO;
import indi.gd.pcmw.service.DoctorService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctors")
@CrossOrigin
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping
    public String save(@RequestBody Doctor doctor){
        doctorService.save(doctor);
        return "register success";
    }

    @GetMapping("/login")
    public String login(@RequestParam("name") String jobId, @RequestParam("password") String password){

        int flag = doctorService.loginValidate(jobId,password);
        if (flag == 1) {
            return "login success";
        }
        else
            return "login name or password error";
    }

    @GetMapping("/getByName")
    public Doctor getDoctorByName(@RequestParam("name") String jobId){
        return doctorService.getDoctorByName(jobId);
    }
    @GetMapping("/getById")
    public DoctorDTO getDoctorById(@RequestParam("id") Integer id){
        return doctorService.getDoctorWithDeptById(id);
    }
    @GetMapping("/getEnhanceDoctor")
    public DoctorDTO getDoctorWithDeptById(@RequestParam("id") Integer id){
        return doctorService.getDoctorWithDeptById(id);
    }
    @PutMapping
    public String updateUser(@RequestBody Doctor doctor){
        if (doctorService.updateDoctor(doctor) == 1)
            return "update success";
        else
            return "update fail";
    }
}
