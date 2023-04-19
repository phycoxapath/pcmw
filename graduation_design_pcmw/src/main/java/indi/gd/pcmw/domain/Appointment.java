package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private int id;
    private String appointType;
    private int initiatorId;
    private int handlerId;
    private Date operateTime;
    private Date appointTime;
}
