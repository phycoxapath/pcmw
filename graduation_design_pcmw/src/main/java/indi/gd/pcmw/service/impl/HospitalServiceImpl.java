package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    HospitalDao hospitalDao;

    @Override
    public User getHospitalHospitalName(String hospitalName) {
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
