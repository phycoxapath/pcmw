package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.ApplyDao;
import indi.gd.pcmw.dao.DoctorDao;
import indi.gd.pcmw.dao.HospitalDao;
import indi.gd.pcmw.dao.UserDao;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.dto.ApplyDTO;
import indi.gd.pcmw.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private DoctorDao doctorDao;
    @Autowired
    private HospitalDao hospitalDao;
    @Override
    public int insertApplication(Apply apply) {
        return applyDao.insertApplication(apply);
    }

    @Override
    public List<ApplyDTO> getApplicationByInitiatorId(Integer initiatorId, String role) {
        List<ApplyDTO> applyList = applyDao.getApplicationByInitiatorId(initiatorId);
        if (role.equals("users")){
            for (ApplyDTO application: applyList
                 ) {
                application.setInitiatorName(userDao.getUserById(initiatorId).getLoginName());
                //admin handle
            }
        } else if (role.equals("doctors")) {
            for (ApplyDTO application: applyList
                 ) {
                application.setInitiatorName(doctorDao.getDoctorById(initiatorId).getJobId()+"");
                application.setHandlerName(hospitalDao.getHospitalById(initiatorId).getHospitalName());
            }
        }else {
            for (ApplyDTO application: applyList
            ) {
                application.setInitiatorName(hospitalDao.getHospitalById(initiatorId).getHospitalName());
                //admin handle
            }
        }
        return applyList;
    }

    @Override
    public List<ApplyDTO> getApplicationByHandlerId(Integer handlerId, String role) {
        List<ApplyDTO> applyList = applyDao.getApplicationByHandlerId(handlerId);
        if (role.equals("hospitals")){
            for (ApplyDTO application: applyList
            ) {
                application.setInitiatorName(doctorDao.getDoctorById(application.getInitiatorId()).getJobId()+"");
                application.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
                //
            }
        } else {
           //admin
        }
        return applyList;
    }

    @Override
    public int updateApplication(Apply apply) {
        return applyDao.updateApplication(apply);
    }
}
