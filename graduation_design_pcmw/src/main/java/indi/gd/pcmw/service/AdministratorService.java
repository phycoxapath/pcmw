package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.User;

import javax.servlet.http.HttpServletResponse;

public interface AdministratorService {
    int adminLoginValidate(User user);

}
