package indi.gd.pcmw.dto;

import indi.gd.pcmw.domain.Appointment;

public class AppointmentDTO extends Appointment {
    private String initiatorName;
    private String handlerName;

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
