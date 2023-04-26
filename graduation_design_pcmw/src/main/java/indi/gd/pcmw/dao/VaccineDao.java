package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.VaccineDynamicUpdate;
import indi.gd.pcmw.domain.Vaccine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface VaccineDao {
    @Insert("insert into pcmw_vaccine values(#{id}, #{vaccineName}, #{vaccinePrice}, #{vaccineDescription}, #{vaccinateDay}, #{prepareCompany}, #{hospId})")
    int save(Vaccine vaccine);

    @Select("select * from pcmw_vaccine where hosp_id = #{hospId}")
    List<Vaccine> getVaccinesByHospId(Integer hospId);

    @Delete("delete from pcmw_vaccine where id= #{id}")
    int deleteVaccineById(Integer id);

    @UpdateProvider(type = VaccineDynamicUpdate.class, method = "updateVaccine")
    int updateVaccine(Vaccine vaccine);
}
