package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int id;
    private String orderId;
    private int userId;
    private int commodityId;
    private Date orderTime;

}
