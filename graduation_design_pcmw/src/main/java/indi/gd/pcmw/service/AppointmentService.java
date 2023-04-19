package indi.gd.pcmw.service;

import indi.gd.pcmw.dto.AppointmentDTO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppointmentService {
    int save();

    List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId, String role);

    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId, String role);

    List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId, String role);

    List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId, String role);
}
