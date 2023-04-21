package indi.gd.pcmw.dto;

import indi.gd.pcmw.domain.Appointment;
import indi.gd.pcmw.domain.Department;

public class AppointmentDTO extends Appointment {
    private String initiatorName;
    private String handlerName;

    private Department handlerDept;

    public Department getHandlerDept() {
        return handlerDept;
    }

    public void setHandlerDept(Department handlerDept) {
        this.handlerDept = handlerDept;
    }
    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }
}
