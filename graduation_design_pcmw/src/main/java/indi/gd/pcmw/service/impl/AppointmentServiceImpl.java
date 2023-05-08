package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.AppointmentDao;
import indi.gd.pcmw.dao.DoctorDao;
import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.dao.UserDao;
import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.domain.Doctor;
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
    public int save(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    @Override
    public int deleteAppointmentById(Integer deleteId) {
        return appointmentDao.deleteAppointmentById(deleteId);
    }

    @Override
    public int getCountByHandlerId(Integer handlerId) {
        return appointmentDao.getCountByHandlerId(handlerId);
    }

    @Override
    public int updateAppointmentState(Integer id, String state) {
        return appointmentDao.updateAppointmentState(id, state);
    }

    //只有用户角色发起预约，预约类型为普通挂号和疫苗预约以及绿色通道
    @Override
    public List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId, String type) {
        List<AppointmentDTO> validAppointments = appointmentDao.getValidAppointmentByInitiatorId(initiatorId);
        Iterator<AppointmentDTO> iterator = validAppointments.listIterator();
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                if (!type.equals(appointment.getAppointType())){
                    iterator.remove();
                    continue;
                }
                if (type.equals("普通预约")) {
                    Doctor doc = doctorDao.getDoctorById(appointment.getHandlerId());
                    appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                    appointment.setHandlerName(doc.getDocName());
                    appointment.setHandlerDept(doctorDao.getDeptById(doc.getDeptId()));
                }
                else {
                    appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                    appointment.setHandlerName(hospitalDao.getHospitalById(appointment.getHandlerId()).getHospitalName());
                }

            }

        return validAppointments;
    }

    @Override
    public List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId, String type) {
        List<AppointmentDTO> overdueAppointments = appointmentDao.getOverdueAppointmentByInitiatorId(initiatorId);
        Iterator<AppointmentDTO> iterator = overdueAppointments.listIterator();
        while (iterator.hasNext()) {
            AppointmentDTO appointment = iterator.next();
            if (!type.equals(appointment.getAppointType())){
                iterator.remove();
                continue;
            }
            if (type.equals("普通预约")) {
                Doctor doc = doctorDao.getDoctorById(appointment.getHandlerId());
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(doc.getDocName());
                appointment.setHandlerDept(doctorDao.getDeptById(doc.getDeptId()));
            }
            else {
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(appointment.getHandlerId()).getHospitalName());
            }

        }

        return overdueAppointments;
    }

    //有两个角色（doctors、hospitals）能进行预约处理
    @Override
    public List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId, String role) {
        List<AppointmentDTO> validAppointments = appointmentDao.getValidAppointmentByHandlerId(handlerId);
        Iterator<AppointmentDTO> iterator = validAppointments.listIterator();
        if (role.equals("doctors")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                if (appointment.getAppointType().equals("绿色通道")){
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
                if (appointment.getAppointType().equals("普通预约")){
                    iterator.remove();
                    continue;
                }
                appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
            }
        }
        return validAppointments;
    }

    @Override
    public List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId, String role) {
        List<AppointmentDTO> overdueAppointments = appointmentDao.getOverdueAppointmentByHandlerId(handlerId);
        Iterator<AppointmentDTO> iterator = overdueAppointments.listIterator();
        if (role.equals("doctors")) {
            while (iterator.hasNext()) {
                AppointmentDTO appointment = iterator.next();
                if (appointment.getAppointType().equals("绿色通道")){
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
        return overdueAppointments;
    }

    @Override
    public List<AppointmentDTO> getValidByInitiatorIdAndType(Integer initiatorId, String type) {
        List<AppointmentDTO> appointments = appointmentDao.getValidByInitiatorIdAndType(initiatorId, type);
        if (type.equals("普通预约")){
            for (AppointmentDTO appointment:appointments
                 ) {
                Doctor doc = doctorDao.getDoctorById(appointment.getHandlerId());
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(doc.getDocName());
                appointment.setHandlerDept(doctorDao.getDeptById(doc.getDeptId()));
            }
        } else if (type.equals("绿色通道") || type.equals("疫苗预约")) {
            for (AppointmentDTO appointment:appointments
            ) {
                appointment.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                appointment.setHandlerName(hospitalDao.getHospitalById(appointment.getHandlerId()).getHospitalName());
            }
        }
            return appointments;
    }

    @Override
    public List<AppointmentDTO> getValidByHandlerIdAndType(Integer handlerId, String type) {
        List<AppointmentDTO> appointments = appointmentDao.getValidByHandlerIdAndType(handlerId, type);
            if (type.equals("普通预约")){
                for (AppointmentDTO appointment:appointments
                ) {
                    appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                    appointment.setHandlerName(doctorDao.getDoctorById(handlerId).getDocName());
                }
            }else {
                for (AppointmentDTO appointment:appointments
                ) {
                    appointment.setInitiatorName(userDao.getUserById(appointment.getInitiatorId()).getUserName());
                    appointment.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
                }
            }
        return appointments;
    }
}
