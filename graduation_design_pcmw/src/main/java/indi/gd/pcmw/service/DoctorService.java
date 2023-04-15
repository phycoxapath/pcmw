package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.dto.DoctorDTO;
import org.apache.ibatis.annotations.Param;

public interface DoctorService {
    int save(Doctor doctor);
    int loginValidate(String jobId, String password);
    Doctor getDoctorByName(String jobId);
    Doctor getDoctorById(Integer id);
    DoctorDTO getDoctorWithDeptById(Integer id);
    int updateDoctor(Doctor doctor);
    int updateDoctorQual(String imageName, Integer id);
}
