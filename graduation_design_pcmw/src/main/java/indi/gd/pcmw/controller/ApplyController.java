package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.dto.ApplyDTO;
import indi.gd.pcmw.service.ApplyService;
import indi.gd.pcmw.service.impl.ApplyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/apply")
@CrossOrigin
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    @PostMapping("/save")
    public String saveApplication(@RequestBody Apply apply){
        int flag = applyService.insertApplication(apply);
        if (flag > 0){
            return "insert success";
        }else
            return "insert fail";

    }
    @GetMapping("/getByInitiatorId")
    public List<ApplyDTO> getApplicationByInitiatorId(@RequestParam("id") Integer initiatorId, @RequestParam("role") String role){
        return applyService.getApplicationByInitiatorId(initiatorId, role);
    }
    @GetMapping("/getByHandlerId")
    public List<ApplyDTO> getApplicationByHandlerId(@RequestParam("id") Integer handlerId, @RequestParam("role") String role){
        return applyService.getApplicationByHandlerId(handlerId, role);
    }
    @PutMapping("/update")
    public String updateApplication(@RequestBody Apply apply){
        int flag = applyService.updateApplication(apply);
        if (flag > 0){
            return "update success";
        }else
            return "update fail";
    }
}
