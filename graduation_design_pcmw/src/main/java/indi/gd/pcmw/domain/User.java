package indi.gd.pcmw.domain;

import lombok.Data;

@Data
public class User {
    private int id;
    private String loginName;
    private String password;
    private String  gender;
    private int age;
    private String address;
    private boolean qualification;
    private String qualType;
}
