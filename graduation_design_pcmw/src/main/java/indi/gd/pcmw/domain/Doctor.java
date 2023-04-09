package indi.gd.pcmw.domain;

import lombok.Data;
import org.springframework.jdbc.core.SqlReturnType;

@Data
public class Doctor {
    private int id;
    private int job_id;
    private String password;
    private char gender;
    private byte workingDay;
    private int deptId;
}
