package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.HospitalService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/{hospitalName}")
    public Hospital getHospitalByHospitalName(@PathVariable("hospitalName") String hospitalName){
        return hospitalService.getHospitalHospitalName(hospitalName);
    }
    @GetMapping("/{hospitalName}/{password}")
    public String login(@PathVariable("hospitalName") String hospitalName, @PathVariable("password") String password){

        int flag = hospitalService.loginValidate(hospitalName,password);
        if (flag == 1) {
            return "login success";
        }
        else
            return "login name or password error";
    }
    @PutMapping
    public String updateHospital(@RequestBody Hospital hospital){
       if (hospitalService.updateHospital(hospital) == 1)
           return "update success";
       else
           return "update fail";
    }

    @PostMapping("/bindDept")
    public String insertDeptBatch(@RequestBody List<Department> departments){
        if (hospitalService.insertDeptBatch(departments) > 0){
            return "insert success";
        }
        else
            return "insert fail";
    }

    @GetMapping("/getById/{id}")
    public Hospital getHospitalById(@PathVariable Integer id){
        Hospital hospital = hospitalService.getHospitalById(id);
        hospital.setDepartments(hospitalService.getDeptByHospId(id));
        return hospital;
    }

}
