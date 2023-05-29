package indi.gd.pcmw.controller;

import indi.gd.pcmw.dao.CommodityDao;
import indi.gd.pcmw.domain.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/commodities")
@CrossOrigin
public class CommodityController {
    @Autowired
    private CommodityDao commodityDao;

    @GetMapping("/getAllCommodity")
    public List<Commodity> getAllCommodity(){
        return commodityDao.getAllCommodity();
    }

}
