package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.DoctorDao;
import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorDao doctorDao;
    @Override
    public int save(Doctor doctor) {
        return doctorDao.save(doctor);
    }
}
