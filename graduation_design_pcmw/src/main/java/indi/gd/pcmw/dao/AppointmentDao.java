package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.dto.AppointmentDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AppointmentDao {
    @Insert("insert into pcmw_appointment(initiator_id,handler_id,appoint_type,appoint_appendix,appoint_time) values(#{initiatorId},#{handlerId},#{appointType},#{appointAppendix},#{appointTime})")
    int save(Appointment appointment);

    @Select("select * from pcmw_appointment where initiator_id = #{initiatorId} and appoint_time > NOW()")
    List<AppointmentDTO> getValidAppointmentByInitiatorId(Integer initiatorId);

    @Select("select * from pcmw_appointment where initiator_id = #{initiatorId} and appoint_time < NOW()")
    List<AppointmentDTO> getOverdueAppointmentByInitiatorId(Integer initiatorId);

    @Select("select * from pcmw_appointment where handler_id = #{handlerId} and appoint_time > NOW()")
    List<AppointmentDTO> getValidAppointmentByHandlerId(Integer handlerId);

    @Select("select * from pcmw_appointment where handler_id = #{handlerId} and appoint_time < NOW()")
    List<AppointmentDTO> getOverdueAppointmentByHandlerId(Integer handlerId);

}
