package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.ApplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdministratorDao {
    @Select("select count(*) from pcmw_user where login_name = #{loginName} and password = #{password}")
    int adminLoginValidate(User user);

    @Select("select * from pcmw_apply where handler_id = #{id} and apply_type = #{type}")
    List<ApplyDTO> getApplyByType(@Param("id") Integer id, @Param("type") String type);
}
