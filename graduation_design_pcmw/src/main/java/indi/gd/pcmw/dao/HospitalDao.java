package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.DeptBatchInsert;
import indi.gd.pcmw.dao.provider.HospNoticeDynamicUpdate;
import indi.gd.pcmw.dao.provider.HospitalDynamicUpdate;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Doctor;
import indi.gd.pcmw.domain.HospNotice;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import indi.gd.pcmw.dto.DoctorDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Mapper
public interface HospitalDao {

    /**
     * @param hospital
     * @return
     * 注册时保存医院
     */
    @Insert("insert into pcmw_hospital values(#{id},#{loginName},#{hospitalName},#{password},#{hospitalDescription},#{qualification},#{qualType},#{qualImage})")
    int save(Hospital hospital);

    /**
     *
     * @param loginName
     * @param password
     * @return
     * 登录验证
     */
    @Select("select count(*) from pcmw_hospital where login_name = #{loginName} and password = #{password}")
    int loginValidate(@Param("loginName") String loginName, @Param("password") String password);

    /**
     *
     * @param loginName
     * @return
     * 通过登录名查找医院信息
     */
    @Select("select * from pcmw_hospital where login_name = #{loginName}")
    Hospital getHospitalByLoginName(String loginName);
    @Select("select * from pcmw_hospital where id = #{id}")
    Hospital getHospitalById(Integer id);
    /**
     *
     * @param hospital
     * @return
     * 动态sql更新医院信息
     */
    @UpdateProvider(type = HospitalDynamicUpdate.class,method = "updateHospital")
    int updateHospital(Hospital hospital);

    /**
     *
     * @param departments
     * @return
     * 动态sql实现批量添加医院下属科室（sql字符串拼接法）
     */
    @InsertProvider(type = DeptBatchInsert.class,method = "batchInsert")
    int insertDeptBatch(List<Department> departments);
    @Delete("delete from pcmw_department where hosp_id = #{hospId} and dept_name = #{deptName}")
    int deleteDept(@Param("deptName") String deptName, @Param("hospId") String hospId);

    @Select("select * from pcmw_department where hosp_id = #{hospId}")
    List<Department> getDeptByHospId(Integer hospId);

    @Update("update pcmw_hospital set qual_image = #{imageName} where id = #{id}")
    int updateHospitalQual(@Param("imageName") String imageName,@Param("id") Integer id);

    @Select("select * from pcmw_hospital")
    List<Hospital> getAllHospitals();

    @Select("select * from pcmw_apply")
    List<Apply> getApplications();

    @Update("update pcmw_doctor set qualification = #{qualification} where id = #{id}")
    int updateDoctorQualification(@Param("id") Integer id ,@Param("qualification") boolean qualification);

    @Select("select * from pcmw_doctor where dept_id = #{deptId}")
    List<DoctorDTO> getDoctorsByDeptId(Integer deptId);

    @Delete("delete from pcmw_doctor where id = #{docId}")
    int deleteDoctorById(Integer docId);

    @Insert("insert into pcmw_hosp_notice(notice_title, notice_main, notice_publisher, hosp_id) values(#{noticeTitle},#{noticeMain},#{noticePublisher},#{hospId})")
    int insertNotice(HospNotice hospNotice);

    @Select("select id, notice_title, notice_publisher, hosp_id, post_time from pcmw_hosp_notice where hosp_id = #{hospId}")
    List<HospNotice> getNoticeListByHospId(Integer hospId);

    @Select("select * from pcmw_hosp_notice where id = #{id}")
    HospNotice getSpecificNoticeById(Integer id);

    @UpdateProvider(type = HospNoticeDynamicUpdate.class, method = "updateHospNotice")
    int updateNotice(HospNotice hospNotice);

    @Delete("delete from pcmw_hosp_notice where id = #{noticeId}")
    int deleteNoticeById(Integer noticeId);
}
