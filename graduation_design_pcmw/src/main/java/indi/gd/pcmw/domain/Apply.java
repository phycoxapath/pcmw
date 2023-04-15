package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Apply {
    private int id;
    private String applicationType;
    private int userId;
    private int docId;
    private int hospId;
    private Date applyTime;
}
