package indi.gd.pcmw.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class HospNotice {
    private Integer id;
    private String noticeTitle;
    private String noticeMain;
    private String noticePublisher;
    private String hospId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;
}
