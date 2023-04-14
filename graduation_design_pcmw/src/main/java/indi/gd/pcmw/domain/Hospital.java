package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.List;

@Data
public class Hospital {
    private int id;
    private String hospitalName;
    private String password;
    private boolean qualification;
    private String qualType;
    private String qualImage;
    private List<Department> departments;
}
