package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.controller.util.JwtUtil;
import indi.gd.pcmw.dao.AdministratorDao;
import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.dao.UserDao;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
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

    @Autowired
    private UserDao userDao;
    @Override
    public int adminLoginValidate(User user) {
        return administratorDao.adminLoginValidate(user);
    }

    @Override
    public List<Hospital> getAllHospitals() {
        return hospitalDao.getAllHospitals();
    }

    @Override
    public List<ApplyDTO> getApplyByType(Integer id, String type) {
        List<ApplyDTO> applications = administratorDao.getApplyByType(id, type);
        String initiatorName;
        for (ApplyDTO application:applications
             ) {
            initiatorName = type.equals("免挂号绿色通道预约资质申请") ?
                    userDao.getUserById(application.getInitiatorId()).getUserName() :
                    hospitalDao.getHospitalById(application.getInitiatorId()).getHospitalName();
            application.setInitiatorName(initiatorName);
            application.setHandlerName(userDao.getUserById(id).getUserName());
        }
        System.out.println(applications);
        return applications;
    }

    @Override
    public int updateHospQual(Integer hospId, boolean qualification) {
        return administratorDao.updateHospQual(hospId, qualification);
    }
}
