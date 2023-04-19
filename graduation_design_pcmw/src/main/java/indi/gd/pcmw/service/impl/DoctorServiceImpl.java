package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.DoctorDao;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.dto.DoctorDTO;
import indi.gd.pcmw.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;

    @Override
    public List<DoctorDTO> getDoctorsByDeptId(Integer deptId) {
        List<DoctorDTO> doctors = doctorDao.getDoctorsByDeptId(deptId);
        for (DoctorDTO doctor:doctors
             ) {
            Department dept = doctorDao.getDeptById(deptId);
            doctor.setHospId(dept.getHospId()+"");
            doctor.setDeptName(dept.getDeptName());
        }
        return doctors;
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        return doctorDao.updateDoctor(doctor);
    }

    @Override
    public int updateDoctorQual(String imageName, Integer id) {
        return doctorDao.updateDoctorQual(imageName, id);
    }

    @Override
    public DoctorDTO getDoctorWithDeptById(Integer id) {
        DoctorDTO doctorDTO = doctorDao.getDoctorWithDeptById(id);
        if (doctorDTO.getDeptId() != 0) {
            Department dept = doctorDao.getDeptById(doctorDTO.getDeptId());
            doctorDTO.setDeptName(dept.getDeptName());
            doctorDTO.setHospId("" + dept.getHospId());
        }
        return doctorDTO;
    }

    @Override
    public int save(Doctor doctor) {
        return doctorDao.save(doctor);
    }

    @Override
    public Doctor getDoctorByName(String jobId) {
        return doctorDao.getDoctorByName(jobId);
    }

    @Override
    public Doctor getDoctorById(Integer id) {
        return doctorDao.getDoctorById(id);
    }

    @Override
    public int loginValidate(String jobId, String password) {
        return doctorDao.loginValidate(jobId, password);
    }
}
