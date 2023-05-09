package indi.gd.pcmw.domain;

import com.sun.source.doctree.SeeTree;
import lombok.Data;

import java.util.List;

@Data
public class Hospital {
    private int id;
    private String loginName;
    private String hospitalName;
    private String password;
    private String hospitalDescription;
    private boolean qualification;
    private String qualType;
    private String qualImage;
    private List<Department> departments;
}
