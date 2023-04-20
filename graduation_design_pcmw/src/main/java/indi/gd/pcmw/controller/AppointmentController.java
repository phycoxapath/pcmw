package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.dto.AppointmentDTO;
import indi.gd.pcmw.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/saveAppointment")
    public String save(@RequestBody Appointment appointment){
        System.out.println(appointment);
       int flag = appointmentService.save(appointment);
       if (flag > 0)
           return "save success";
       else
           return "save fail";
    }

    @GetMapping("/getValidByInitiatorId")
    List<AppointmentDTO> getValidAppointmentByInitiatorId(@RequestParam("initiatorId") Integer initiatorId){
        return appointmentService.getValidAppointmentByInitiatorId(initiatorId);
    }
    @GetMapping("/geOverdueByInitiatorId")
    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(@RequestParam("initiatorId") Integer initiatorId){
        return appointmentService.getOverdueAppointmentByInitiatorId(initiatorId);
    }
    @GetMapping("/getValidByHandlerId")
    List<AppointmentDTO> getValidAppointmentByHandlerId(@RequestParam("handlerId") Integer handlerId,@RequestParam("role") String role){
        return appointmentService.getValidAppointmentByHandlerId(handlerId, role);
    }
    @GetMapping("/getOverdueByHandlerId")
    List<AppointmentDTO> getOverdueAppointmentByHandlerId(@RequestParam("handlerId") Integer handlerId,@RequestParam("role") String role){
        return appointmentService.getOverdueAppointmentByHandlerId(handlerId, role);
    }

}
