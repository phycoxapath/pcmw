package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.DeptBatchInsert;
import indi.gd.pcmw.dao.provider.HospitalDynamicUpdate;
import indi.gd.pcmw.domain.Department;
import indi.gd.pcmw.domain.Hospital;
import indi.gd.pcmw.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface HospitalDao {

    /**
     * @param hospital
     * @return
     * 注册时保存医院
     */
    @Insert("insert into pcmw_hospital values(#{id},#{hospitalName},#{password},#{qualification},#{qualType})")
    int save(Hospital hospital);

    /**
     *
     * @param hospitalName
     * @param password
     * @return
     * 登录验证
     */
    @Select("select count(*) from pcmw_hospital where hospital_name = #{hospitalName} and password = #{password}")
    int loginValidate(@Param("hospitalName") String hospitalName, @Param("password") String password);

    /**
     *
     * @param hospitalName
     * @return
     * 通过医院名查找医院信息
     */
    @Select("select * from pcmw_hospital where hospital_name = #{hospitalName}")
    Hospital getHospitalByHospitalName(String hospitalName);
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
    @Select("select * from pcmw_department where hosp_id = #{hospId}")
    List<Department> getDeptByHospId(Integer hospId);
}
