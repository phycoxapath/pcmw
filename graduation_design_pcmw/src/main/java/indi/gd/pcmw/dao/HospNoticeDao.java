package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.HospNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HospNoticeDao {
    @Select("select * from pcmw_hosp_notice order by post_time desc limit 5")
    List<HospNotice> getRecentHospNotice();
}
