package indi.gd.pcmw.dao.provider;

import cn.hutool.core.util.StrUtil;
import indi.gd.pcmw.domain.HospNotice;
import org.apache.ibatis.jdbc.SQL;

public class HospNoticeDynamicUpdate {

    public String updateHospNotice(final HospNotice hospNotice){
        return new SQL(){{
            UPDATE("pcmw_hosp_notice");
            if (StrUtil.isNotEmpty(hospNotice.getNoticeTitle())){
               SET("notice_title = #{noticeTitle}");
            }
            if (StrUtil.isNotEmpty(hospNotice.getNoticeMain())){
                SET("notice_main = #{noticeMain}");
            }
            if (StrUtil.isNotEmpty(hospNotice.getPostTime().toString())){
                SET("post_time = #{postTime}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
