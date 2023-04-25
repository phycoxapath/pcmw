package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Vaccine;
import indi.gd.pcmw.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vaccines")
@CrossOrigin
public class VaccineController {
    @Autowired
    private VaccineService vaccineService;

    @PostMapping("/saveVaccine")
    public String saveNewVaccine(@RequestBody Vaccine vaccine){
        int flag = vaccineService.save(vaccine);
        return flag > 0 ? "save success" : "save fail";
    }

    @GetMapping("/getByHospId")
    public List<Vaccine> getVaccinesByHospId(@RequestParam("hospId") Integer hospId){
        return vaccineService.getVaccinesByHospId(hospId);
    }

    @DeleteMapping("/deleteVaccine")
    public String deleteVaccineById(@RequestParam("id") Integer id){
        return vaccineService.deleteVaccineById(id) > 0 ? "delete success" : "delete fail";
    }

    @PutMapping("/updateVaccine")
    public String updateVaccine(@RequestBody Vaccine vaccine) {
        return vaccineService.updateVaccine(vaccine) > 0 ? "update success" : "update fail";
    }
}
