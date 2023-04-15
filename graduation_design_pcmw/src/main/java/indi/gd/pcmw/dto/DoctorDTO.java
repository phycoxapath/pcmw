package indi.gd.pcmw.dto;

import indi.gd.pcmw.domain.Doctor;
import lombok.Data;

@Data
public class DoctorDTO {
    private int id;
    private int jobId;
    private String password;
    private String docName;
    private char gender;
    private byte workingDay;
    private String deptName;
    private boolean qualification;
    private String qualType;
    private String qualImage;
    private int deptId;
    private String hospId;
}
