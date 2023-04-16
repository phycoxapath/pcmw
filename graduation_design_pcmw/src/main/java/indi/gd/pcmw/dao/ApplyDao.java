package indi.gd.pcmw.dao;

import indi.gd.pcmw.dao.provider.ApplyDynamicUpdate;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.dto.ApplyDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

@Mapper
public interface ApplyDao {
    @Insert("insert into pcmw_apply(id,apply_type,initiator_id,handler_id,apply_state,apply_image,handle_time) values(#{id},#{applyType},#{initiatorId},#{handlerId},#{applyState},#{applyImage},#{handleTime})")
    int insertApplication(Apply apply);

    @Select("select * from pcmw_apply where initiator_id = #{initiatorId}")
    List<ApplyDTO> getApplicationByInitiatorId(Integer initiatorId);

    @Select("select * from pcmw_apply where handler_id = #{handlerId}")
    List<ApplyDTO> getApplicationByHandlerId(Integer handlerId);

    @UpdateProvider(type = ApplyDynamicUpdate.class, method = "updateApply")
    int updateApplication(Apply apply);
}
