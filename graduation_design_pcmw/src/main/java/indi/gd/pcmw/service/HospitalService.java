package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.HospNotice;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.dto.DoctorDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface HospitalService {
    int save(Hospital hospital);

    int loginValidate(String loginName, String password);

    Hospital getHospitalByLoginName(String loginName);

    Hospital getHospitalById(Integer id);

    int updateHospital(Hospital hospital);

    int insertDeptBatch(List<Department> departments);

    int deleteDept(String deptName, String hospId);

    List<Department> getDeptByHospId(Integer hospId);

    int updateHospitalQual(String imageName, Integer id);

    List<Hospital> getAllHospitals();

    List<Apply> getApplications();

    int updateDoctorQualification(Integer id, boolean qualification);

    List<DoctorDTO> getDoctorsByDeptId(Integer deptId);

    int deleteDoctorById(Integer docId);

    int insertNotice(HospNotice hospNotice);

    List<HospNotice> getNoticeListByHospId(Integer hospId);



}
