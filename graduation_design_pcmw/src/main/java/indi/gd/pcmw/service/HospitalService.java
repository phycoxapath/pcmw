package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Hospital;
import java.util.List;

public interface HospitalService {
    int save(Hospital hospital);
    int loginValidate(String hospitalName, String password);
    Hospital getHospitalHospitalName(String hospitalName);
    Hospital getHospitalById(Integer id);
    int updateHospital(Hospital hospital);

    int insertDeptBatch(List<Department> departments);

    List<Department> getDeptByHospId(Integer hospId);
}
