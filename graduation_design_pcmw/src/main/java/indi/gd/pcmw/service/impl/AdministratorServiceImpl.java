package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.controller.util.JwtUtil;
import indi.gd.pcmw.dao.AdministratorDao;
import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Autowired
    private AdministratorDao administratorDao;
    @Autowired
    private HospitalDao hospitalDao;
    @Override
    public int adminLoginValidate(User user) {
        return administratorDao.adminLoginValidate(user);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalDao.getAllHospitals();
    }
}
