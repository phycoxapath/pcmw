package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;

public interface HospitalService {
    int save(Hospital hospital);

    int loginValidate(String hospitalName, String password);
    User getHospitalHospitalName(String hospitalName);
}
