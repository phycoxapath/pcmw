package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.User;

public interface UserService {

    int save(User user);

    int loginValidate(String name,String password);

    User getUserByLoginName(String loginName);

    User getModifiedAttr(String loginName);

    int updateUser(User user);
}
