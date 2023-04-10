package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.UserDao;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public int loginValidate(String name, String password) {
       return userDao.loginValidate(name,password);
    }
    @Override
    public int save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        return userDao.getUserByLoginName(loginName);
    }
}