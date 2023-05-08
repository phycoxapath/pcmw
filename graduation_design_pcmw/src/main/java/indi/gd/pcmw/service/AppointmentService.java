package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.dto.AppointmentDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppointmentService {
    int save(Appointment appointment);

    int deleteAppointmentById(Integer deleteId);

    int getCountByHandlerId(Integer handlerId);

    List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId, String type);

    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId, String type);

    List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId, String role);

    List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId, String role);

    List<AppointmentDTO> getValidByInitiatorIdAndType(Integer initiatorId, String type);

    List<AppointmentDTO> getValidByHandlerIdAndType(Integer handlerId, String type);

    int updateAppointmentState(Integer id, String state);




}
