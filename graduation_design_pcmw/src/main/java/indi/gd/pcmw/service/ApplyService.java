package indi.gd.pcmw.service;

import indi.gd.pcmw.dao.provider.ApplyDynamicUpdate;
import indi.gd.pcmw.domain.Apply;
import indi.gd.pcmw.dto.ApplyDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

public interface ApplyService {

    int insertApplication(Apply apply);

    List<ApplyDTO> getApplicationByInitiatorId(Integer initiatorId, String role);

    List<ApplyDTO> getApplicationByHandlerId(Integer handlerId, String role);

    int updateApplication(Apply apply);
}
