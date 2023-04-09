package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Vaccinate {
    private int id;
    private String vaccinateId;
    private int vaccineId;
    private int userId;
    private Date vaccinateTime;
}
