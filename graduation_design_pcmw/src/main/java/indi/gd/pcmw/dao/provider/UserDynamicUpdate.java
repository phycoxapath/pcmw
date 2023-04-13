package indi.gd.pcmw.dao.provider;


import indi.gd.pcmw.domain.User;
import org.apache.ibatis.jdbc.SQL;

/*
*
 */
public class UserDynamicUpdate {
    public String updateUser(final User user){
        return new SQL(){{
            UPDATE("pcmw_user");
            if (user.getPassword() != null && !user.getPassword().equals("")) {
                SET("password = #{password}");
            }
            if (user.getLoginName() != null && user.getLoginName().equals("")){
                SET("login_name = #{loginName}");
            }
            if (user.getAge() != 0){
                SET("age = #{age}");
            }
            if (user.getGender() != null && !user.getGender().equals("")){
                SET("gender = #{gender}");
            }
            if (user.getAddress() != null && !user.getAddress().equals("")){
                SET("address = #{address}");
            }
            //忘记密码重置密码时处于未登录状态，获取不到id
            WHERE("login_name = #{loginName} or id = #{id}");

        }}.toString();
    }
}
