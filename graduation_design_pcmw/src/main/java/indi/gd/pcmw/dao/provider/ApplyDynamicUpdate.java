package indi.gd.pcmw.dao.provider;

import indi.gd.pcmw.domain.Apply;
import org.apache.ibatis.jdbc.SQL;

public class ApplyDynamicUpdate {
    public String updateApply(final Apply apply){
        return new SQL(){{
            UPDATE("pcmw_apply");
            if (apply.getApplyImage() != null && !apply.getApplyImage().equals("")){
                SET("apply_image = #{applyImage}");
            }
            if (apply.getApplyState() != null && !apply.getApplyState().equals("")){
                SET("apply_state = #{applyState}");
            }
            if (apply.getHandleTime() != null){
                SET("handle_time = #{handleTime}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
