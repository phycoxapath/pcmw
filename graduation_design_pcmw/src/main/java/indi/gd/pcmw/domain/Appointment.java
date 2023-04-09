package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private int id;
    private int userId;
    private String appointId;
    private String appointType;
    private int docJobId;
    private int vaccineId;
    private Date appointTime;
}
