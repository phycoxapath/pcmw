package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Hospital;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HospitalDao {

    @Insert("insert into pcmw_hospital values(#{id},#{hospitalName},#{password},#{qualification},#{qualType})")
    int save(Hospital hospital);
}
