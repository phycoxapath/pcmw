package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Commodity;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdministratorDao {
    @Select("select count(*) from pcmw_user where login_name = #{loginName} and password = #{password}")
    int adminLoginValidate(User user);

    @Select("select * from pcmw_apply where handler_id = #{id} and apply_type = #{type}")
    List<ApplyDTO> getApplyByType(@Param("id") Integer id, @Param("type") String type);

    @Update("update pcmw_hospital set qualification = #{qualification} where id = #{hospId}")
    int updateHospQual(@Param("hospId") Integer hospId, @Param("qualification") boolean qualification);
    @Update("update pcmw_user set qualification = #{qualification} where id = #{userId}")
    int updateUserQual(@Param("userId") Integer userId, @Param("qualification") boolean qualification);
    @Delete("delete from pcmw_apply where id = #{id}")
    int deleteApplication(Integer id);

    @Select("select * from pcmw_user where id != 4")
    List<User> getAllUsers();

    @Delete("delete from pcmw_user where id = #{id}")
    int deleteUserById(Integer id);

    @Insert("insert into pcmw_commodity values(#{id},#{commodityName},#{commodityCategory},#{commodityDescription},#{commodityPrice},#{commodityStock},#{commodityImage})")
    int saveCommodity(Commodity commodity);

    @Select("select * from pcmw_commodity")
    List<Commodity> getAllCommodity();
}
