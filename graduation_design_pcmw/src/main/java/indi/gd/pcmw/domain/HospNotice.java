package indi.gd.pcmw.domain;

import lombok.Data;

import java.util.Date;
@Data
public class HospNotice {
    private Integer id;
    private String noticeTitle;
    private String noticeMain;
    private String noticePublisher;
    private String hospId;
    private Date postTime;
}
