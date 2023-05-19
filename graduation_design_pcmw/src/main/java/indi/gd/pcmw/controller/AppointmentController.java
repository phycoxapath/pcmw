package indi.gd.pcmw.controller;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.dto.AppointmentDTO;
import indi.gd.pcmw.service.AppointmentService;
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
    @DeleteMapping("/deleteAppointment")
    public String delete(@RequestParam("id") Integer deleteId){
        int flag = appointmentService.deleteAppointmentById(deleteId);
        if (flag > 0)
            return "delete success";
        else
            return "delete fail";
    }

    @PutMapping("/updateAppointmentState")
    public String update(@RequestParam("id") Integer id, @RequestParam("state") String state){
        int flag = appointmentService.updateAppointmentState(id, state);
        if (flag > 0)
            return "update success";
        else
            return "update fail";
    }
    @GetMapping("/getCountByHandlerId")
    public int getCountByHandlerId(@RequestParam("id") Integer handlerId){
        return appointmentService.getCountByHandlerId(handlerId);
    }
    @GetMapping("/getValidByInitiatorId")
    List<AppointmentDTO> getValidAppointmentByInitiatorId(@RequestParam("initiatorId") Integer initiatorId, @RequestParam("type") String type){
        return appointmentService.getValidAppointmentByInitiatorId(initiatorId, type);
    }
    @GetMapping("/getOverdueByInitiatorId")
    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(@RequestParam("initiatorId") Integer initiatorId, @RequestParam("type") String type){
        return appointmentService.getOverdueAppointmentByInitiatorId(initiatorId, type);
    }
    @GetMapping("/getValidByHandlerId")
    List<AppointmentDTO> getValidAppointmentByHandlerId(@RequestParam("handlerId") Integer handlerId,@RequestParam("role") String role){
        return appointmentService.getValidAppointmentByHandlerId(handlerId, role);
    }
    @GetMapping("/getOverdueByHandlerId")
    List<AppointmentDTO> getOverdueAppointmentByHandlerId(@RequestParam("handlerId") Integer handlerId,@RequestParam("role") String role){
        return appointmentService.getOverdueAppointmentByHandlerId(handlerId, role);
    }

    @GetMapping("/getValidByInitiatorIdAndType")
    List<AppointmentDTO> getValidByInitiatorIdAndType(@RequestParam("initiatorId") Integer initiatorId,@RequestParam("type") String type){
        return appointmentService.getValidByInitiatorIdAndType(initiatorId, type);
    }
    @GetMapping("/getValidByHandlerIdAndType")
    List<AppointmentDTO> getValidByHandlerIdAndType(@RequestParam("handlerId") Integer handlerId,@RequestParam("type") String type){
        return appointmentService.getValidByHandlerIdAndType(handlerId, type);
    }

}
