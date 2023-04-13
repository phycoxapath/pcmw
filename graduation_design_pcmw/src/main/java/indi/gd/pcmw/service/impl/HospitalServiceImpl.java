package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalDao hospitalDao;

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
    public Hospital getHospitalHospitalName(String hospitalName) {
        return hospitalDao.getHospitalByHospitalName(hospitalName);
    }


    @Override
    public int save(Hospital hospital) {
        return hospitalDao.save(hospital);
    }

    public int loginValidate(String hospitalName, String password){
        return hospitalDao.loginValidate(hospitalName, password);
    }
}
