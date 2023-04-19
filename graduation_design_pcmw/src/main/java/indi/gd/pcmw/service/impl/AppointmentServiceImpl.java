package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.AppointmentDao;
import indi.gd.pcmw.dao.DoctorDao;
import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.dao.UserDao;
import indi.gd.pcmw.dto.AppointmentDTO;
import indi.gd.pcmw.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentDao appointmentDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private HospitalDao hospitalDao;
    @Autowired
    private UserDao userDao;
    @Override
    public int save() {
        return appointmentDao.save();
    }

    @Override
    public List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId, String role) {
        List<AppointmentDTO> validAppointments = appointmentDao.getValidAppointmentByInitiatorId(initiatorId);
        Iterator<AppointmentDTO> iterator = validAppointments.listIterator();
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();

                if (appointment.getAppointType().equals("")) {
                    appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                    appointment.setHandlerName(doctorDao.getDoctorById(appointment.getHandlerId()).getDocName());
                }
                else if (appointment.getAppointType().equals("1")) {
                    appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                    appointment.setHandlerName(hospitalDao.getHospitalById(appointment.getHandlerId()).getHospitalName());
                }

            }

        return validAppointments;
    }

    @Override
    public List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId, String role) {
        List<AppointmentDTO> overdueAppointments = appointmentDao.getOverdueAppointmentByInitiatorId(initiatorId);
        Iterator<AppointmentDTO> iterator = overdueAppointments.listIterator();
        while (iterator.hasNext()) {
            AppointmentDTO appointment = iterator.next();

            if (appointment.getAppointType().equals("")) {
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(doctorDao.getDoctorById(appointment.getHandlerId()).getDocName());
            }
            else if (appointment.getAppointType().equals("1")) {
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(appointment.getHandlerId()).getHospitalName());
            }

        }

        return overdueAppointments;
    }

    @Override
    public List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId, String role) {
        List<AppointmentDTO> validAppointments = appointmentDao.getValidAppointmentByHandlerId(handlerId);
        Iterator<AppointmentDTO> iterator = validAppointments.listIterator();
        if (role.equals("doctors")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                if (appointment.getAppointType().equals("")){
                    iterator.remove();
                    continue;
                }
                appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                appointment.setHandlerName(doctorDao.getDoctorById(handlerId).getDocName());
            }
        }
        else if (role.equals("hospitals")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
            }
        }
        return validAppointments;
    }

    @Override
    public List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId, String role) {
        List<AppointmentDTO> overdueAppointments = appointmentDao.getValidAppointmentByHandlerId(handlerId);
        Iterator<AppointmentDTO> iterator = overdueAppointments.listIterator();
        if (role.equals("doctors")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                appointment.setHandlerName(doctorDao.getDoctorById(handlerId).getDocName());
            }
        }
        else if (role.equals("hospitals")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
            }
        }
        return overdueAppointments;
    }
}
