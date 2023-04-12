package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HospitalDao {

    @Insert("insert into pcmw_hospital values(#{id},#{hospitalName},#{password},#{qualification},#{qualType})")
    int save(Hospital hospital);
    @Select("select count(*) from pcmw_hospital where hospital_name = #{hospitalName} and password = #{password}")
    int loginValidate(@Param("hospitalName") String hospitalName, @Param("password") String password);
    @Select("select * from pcmw_hospital where hospital_name = #{hospitalName}")
    User getHospitalByHospitalName(String hospitalName);
}
