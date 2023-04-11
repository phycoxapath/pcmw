package indi.gd.pcmw.dao.provider;


import indi.gd.pcmw.domain.User;
import org.apache.ibatis.jdbc.SQL;

/*
*
 */
public class DynamicUpdate {
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
            WHERE("login_name = #{loginName}");

        }}.toString();
    }
}
