package indi.gd.pcmw.domain;

import lombok.Data;

@Data
public class Hospital {
    private int id;
    private String hospitalName;
    private String password;
    private boolean qualification;
    private String qualType;
}
