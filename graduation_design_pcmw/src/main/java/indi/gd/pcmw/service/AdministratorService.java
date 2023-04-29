package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdministratorService {
    int adminLoginValidate(User user);

    List<Hospital> getAllHospitals();

}
