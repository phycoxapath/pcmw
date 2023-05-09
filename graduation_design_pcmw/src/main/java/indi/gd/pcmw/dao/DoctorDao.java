package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.DoctorDynamicUpdate;
import indi.gd.pcmw.dao.provider.HospitalDynamicUpdate;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.dto.DoctorDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface DoctorDao {
    @Insert("insert into pcmw_doctor values(#{id},#{jobId},#{password},#{docName},#{gender},#{docTitle},#{workingDay},#{deptId},#{docProfile},#{qualification},#{qualType},#{qualImage})")
    int save(Doctor doctor);

    @Select("select count(*) from pcmw_doctor where job_id = #{jobId} and password = #{password}")
    int loginValidate(@Param("jobId") String jobId, @Param("password") String password);
    @Select("select * from pcmw_doctor where job_id = #{jobId}")
    Doctor getDoctorByName(String jobId);
    @Select("select * from pcmw_doctor where id = #{id}")
    Doctor getDoctorById(Integer id);
    @Select("select * from pcmw_department where id = #{id}")
    Department getDeptById(Integer id);
    @Select("select * from pcmw_doctor where id = #{id}")
    DoctorDTO getDoctorWithDeptById(Integer id);
    @UpdateProvider(type = DoctorDynamicUpdate.class,method = "updateDoctor")
    int updateDoctor(Doctor doctor);
    @Update("update pcmw_doctor set qual_image = #{imageName} where id = #{id}")
    int updateDoctorQual(@Param("imageName") String imageName,@Param("id") Integer id);
    @Select("select * from pcmw_doctor where dept_id = #{deptId}")
    List<DoctorDTO> getDoctorsByDeptId(Integer deptId);
}
