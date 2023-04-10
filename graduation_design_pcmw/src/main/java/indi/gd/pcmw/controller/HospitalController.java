package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.HospitalService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @PostMapping
    public String save(@RequestBody Hospital hospital){
        hospitalService.save(hospital);
        return "register success";
    }
}
