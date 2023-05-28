package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Commodity;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorService {
    int adminLoginValidate(User user);

    List<Hospital> getAllHospitals();

    List<ApplyDTO> getApplyByType(Integer id, String type);

    int updateHospQual(Integer hospId, boolean qualification);
    int updateUserQual(Integer userId, boolean qualification);

    int deleteApplication(Integer id);

    List<User> getAllUsers();

    int deleteUserById(Integer id);

    int saveCommodity(Commodity commodity);


}
