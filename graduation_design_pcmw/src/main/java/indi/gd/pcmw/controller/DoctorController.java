package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.DoctorService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
