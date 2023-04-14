package indi.gd.pcmw.dao.provider;

import indi.gd.pcmw.domain.User;
import org.apache.ibatis.jdbc.SQL;

public class UserDynamicSelect {
    public String selectUser(final String prop){
        return new SQL(){{
            SELECT("*");
            FROM("pcmw_user");

        }}.toString();
    }
}
