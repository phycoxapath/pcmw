package indi.gd.pcmw.dao;

import indi.gd.pcmw.domain.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityDao {
    @Select("select * from pcmw_commodity")
    List<Commodity> getAllCommodity();
}
