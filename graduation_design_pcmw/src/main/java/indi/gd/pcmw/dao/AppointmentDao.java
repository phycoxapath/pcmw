package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.dto.AppointmentDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppointmentDao {
    @Insert("insert into pcmw_appointment(initiator_id,handler_id,appoint_type,appoint_appendix,appoint_time,appoint_state) values(#{initiatorId},#{handlerId},#{appointType},#{appointAppendix},#{appointTime},#{appointState})")
    int save(Appointment appointment);

    @Delete("delete from pcmw_appointment where id = #{deleteId}")
    int deleteAppointmentById(Integer deleteId);

    @Select("select count(*) from pcmw_appointment where handler_id = #{handlerId} and appoint_type = '普通预约' and appoint_time > NOW()")
    int getCountByHandlerId(Integer handlerId);

    @Select("select * from pcmw_appointment where initiator_id = #{initiatorId} and appoint_time > NOW()")
    List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId);

    @Select("select * from pcmw_appointment where initiator_id = #{initiatorId} and appoint_time < NOW()")
    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId);

    @Select("select * from pcmw_appointment where handler_id = #{handlerId} and appoint_time > NOW()")
    List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId);

    @Select("select * from pcmw_appointment where handler_id = #{handlerId} and appoint_time < NOW()")
    List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId);

}
