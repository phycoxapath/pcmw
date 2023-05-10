package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.HospNotice;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.DoctorDTO;
import indi.gd.pcmw.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalDao hospitalDao;

    @Override
    public int updateDoctorQualification(Integer id, boolean qualification) {
        return hospitalDao.updateDoctorQualification(id, qualification);
    }

    @Override
    public List<Apply> getApplications() {
        return hospitalDao.getApplications();
    }

    @Override
    public List<Hospital> getAllHospitals() {
        List<Hospital> hospitals =  hospitalDao.getAllHospitals();
        for (Hospital hosp:hospitals
             ) {
            hosp.setDepartments(hospitalDao.getDeptByHospId(hosp.getId()));
        }
        return hospitals;
    }

    @Override
    public int deleteDept(String deptName, String hospId) {
        return hospitalDao.deleteDept(deptName, hospId);
    }

    @Override
    public int updateHospitalQual(String imageName, Integer id) {
        return hospitalDao.updateHospitalQual(imageName, id);
    }

    @Override
    public Hospital getHospitalById(Integer id) {
        return hospitalDao.getHospitalById(id);
    }

    @Override
    public List<Department> getDeptByHospId(Integer hospId) {
        return hospitalDao.getDeptByHospId(hospId);
    }

    @Override
    public int insertDeptBatch(List<Department> departments) {
        return hospitalDao.insertDeptBatch(departments);
    }

    @Override
    public int updateHospital(Hospital hospital) {
        return hospitalDao.updateHospital(hospital);
    }

    @Override
    public Hospital getHospitalByLoginName(String loginName) {
        return hospitalDao.getHospitalByLoginName(loginName);
    }


    @Override
    public int save(Hospital hospital) {
        return hospitalDao.save(hospital);
    }

    public int loginValidate(String loginName, String password){
        return hospitalDao.loginValidate(loginName, password);
    }

    @Override
    public List<DoctorDTO> getDoctorsByDeptId(Integer deptId) {
        return hospitalDao.getDoctorsByDeptId(deptId);
    }

    @Override
    public int deleteDoctorById(Integer docId) {
        return hospitalDao.deleteDoctorById(docId);
    }

    @Override
    public int insertNotice(HospNotice hospNotice) {
        return hospitalDao.insertNotice(hospNotice);
    }
}
