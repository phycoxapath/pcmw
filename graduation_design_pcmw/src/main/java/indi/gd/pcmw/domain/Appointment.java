package indi.gd.pcmw.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private int id;
    private int initiatorId;
    private int handlerId;
    private String appointType;
    private String appointAppendix;
    private Date operateTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appointTime;
}
