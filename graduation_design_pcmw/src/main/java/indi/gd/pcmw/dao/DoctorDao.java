package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorDao {
    @Insert("insert into pcmw_doctor values(#{id},#{jobId},#{password},#{docName},#{gender},#{workingDay},#{deptId},#{qualification},#{qualType})")
    int save(Doctor doctor);
}
