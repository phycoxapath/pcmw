package indi.gd.pcmw.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Commodity {
    private int id;
    private String commodityName;
    private String commodityCategory;
    private String commodityDescription;
    private BigDecimal commodityPrice;
    private int commodityStock;
    private String commodityImage;
}
