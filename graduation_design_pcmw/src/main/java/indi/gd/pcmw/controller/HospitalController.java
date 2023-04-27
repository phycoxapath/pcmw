package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin
public class HospitalController {
    @Autowired
    HospitalService hospitalService;
    @Value("${HospitalQualPath}")
    private String path;
    @PostMapping
    public String save(@RequestBody Hospital hospital){
        hospitalService.save(hospital);
        return "register success";
    }

    @GetMapping("/getByName")
    public Hospital getHospitalByHospitalName(@RequestParam("name") String hospitalName){
        return hospitalService.getHospitalHospitalName(hospitalName);
    }
    @GetMapping("/login")
    public String login(@RequestParam("name") String hospitalName, @RequestParam("password") String password){

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

    @DeleteMapping("/deleteDept")
    public String deleteDept(@RequestParam("deptName") String deptName,@RequestParam("hospId") String hospId){
        if (hospitalService.deleteDept(deptName, hospId) > 0){
            return "delete success";
        }else
            return "delete fail";
    }

    @GetMapping("/getById")
    public Hospital getHospitalById(@RequestParam Integer id){
        Hospital hospital = hospitalService.getHospitalById(id);
        hospital.setDepartments(hospitalService.getDeptByHospId(id));
        return hospital;
    }
    @PostMapping("/upload")
    public String uploadReceive(MultipartFile file , @RequestParam("id") Integer id){
        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();
        File dir = new File(path);
        String imageName = path + fileName;
        int flag;
        if (!dir.exists())
            dir.mkdirs();

        try {
            file.transferTo(new File(imageName));
            flag = hospitalService.updateHospitalQual(imageName,id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (flag > 0)
            return imageName;
        else
            return "upload fail";
    }

    @GetMapping("/getAllHospital")
    public List<Hospital> getAll(){
        return hospitalService.getAllHospitals();
    }

    @GetMapping("/getApplications")
    public List<Apply> getApplications(){
        return hospitalService.getApplications();
    }

    @PutMapping("/updateDocQual")
    public String updateDoctorQualification(@RequestParam("docId") Integer docId, @RequestParam("qualification") boolean qualification){
        int flag = hospitalService.updateDoctorQualification(docId, qualification);
        if (flag > 0)
            return "update success";
        else
            return "update fail";

    }

}
