package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdministratorDao {
    @Select("select count(*) from pcmw_user where login_name = #{loginName} and password = #{password}")
    int adminLoginValidate(User user);
}
