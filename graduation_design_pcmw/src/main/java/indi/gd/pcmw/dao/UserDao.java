package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.UserDynamicUpdate;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

@Mapper
public interface UserDao {

    /**
     * 保存用户
     */
    @Insert("insert into pcmw_user values(#{id},#{loginName},#{password},#{userName},#{gender},#{age},#{address},#{qualification},#{qualType},#{qualImage})")
    int save(User user);

    @Select("select count(*) from pcmw_user where login_name = #{loginName} and password = #{password}")
    int loginValidate(@Param("loginName") String loginName,@Param("password") String password);
    @Select("select * from pcmw_user where id = #{id}")
    User getUserById(Integer id);
    @Select("select * from pcmw_user where login_name = #{loginName}")
    User getUserByLoginName(String loginName);
//    @SelectProvider
//    User getUserByAnyProp(String prop);
    @Select("select login_name,gender,age,address from pcmw_user where login_name = #{loginName}")
    User getModifiedAttr(String loginName);

    /**
     *
     * UpdateProvider实现动态sql更新User
     */
    @UpdateProvider(type = UserDynamicUpdate.class, method = "updateUser")
    int updateUser(User user);

    @Update("update pcmw_user set qual_image = #{imageName} where id = #{id}")
    int updateUserQual(@Param("imageName") String imageName, @Param("id") Integer id);
    @Insert("insert into pcmw_apply(id,apply_type,initiator_id,handler_id,apply_state,apply_image) values(#{id},#{applyType},#{initiatorId},#{handlerId},#{applyState},#{applyImage})")
    int insertApplication(Apply apply);
}
