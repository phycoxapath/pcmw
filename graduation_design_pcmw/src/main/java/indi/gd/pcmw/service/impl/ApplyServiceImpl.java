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

import java.util.Iterator;
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
        Iterator<ApplyDTO> iterator = applyList.listIterator();
        if (role.equals("users")){
            while (iterator.hasNext()){
                ApplyDTO applyDTO = iterator.next();
                if (!applyDTO.getApplyType().equals("免挂号绿色通道预约资质申请")){
                    iterator.remove();
                    continue;
                }
                applyDTO.setInitiatorName(userDao.getUserById(initiatorId).getUserName());
                applyDTO.setHandlerName(userDao.getUserById(applyDTO.getHandlerId()).getUserName());
            }

        } else if (role.equals("doctors")) {
            while (iterator.hasNext()){
                ApplyDTO applyDTO = iterator.next();
                if (!applyDTO.getApplyType().equals("从业资格认证申请")){
                    iterator.remove();
                    continue;
                }
                applyDTO.setInitiatorName(doctorDao.getDoctorById(initiatorId).getDocName());
                applyDTO.setHandlerName(hospitalDao.getHospitalById(applyDTO.getHandlerId()).getHospitalName());
            }

        }else {
            while (iterator.hasNext()){
                ApplyDTO applyDTO = iterator.next();
                if (!applyDTO.getApplyType().equals("机构资质认证申请")){
                    iterator.remove();
                    continue;
                }
                applyDTO.setInitiatorName(hospitalDao.getHospitalById(initiatorId).getHospitalName());
                applyDTO.setHandlerName(userDao.getUserById(applyDTO.getHandlerId()).getUserName());

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
                application.setInitiatorName(doctorDao.getDoctorById(application.getInitiatorId()).getDocName());
                application.setHandlerName(hospitalDao.getHospitalById(handlerId).getHospitalName());
                //
            }
        } else {
           //admin
            for (ApplyDTO application: applyList
            ) {
                if (application.getApplyType().equals("免挂号绿色通道预约资质申请"))
                    application.setInitiatorName(userDao.getUserById(application.getInitiatorId()).getUserName());
                else
                    application.setInitiatorName(hospitalDao.getHospitalById(application.getInitiatorId()).getHospitalName());
                application.setHandlerName(userDao.getUserById(handlerId).getUserName());
            }
        }
        return applyList;
    }

    @Override
    public int updateApplication(Apply apply) {
        return applyDao.updateApplication(apply);
    }
}
