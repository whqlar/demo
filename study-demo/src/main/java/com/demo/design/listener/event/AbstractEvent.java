package com.demo.design.listener.event;

import java.util.Date;

/**
 * Created by wu on 16/11/22.
 */
public abstract class AbstractEvent implements Event {

    private Date eventDate;  //事件发生事件

    private Object source;  //事件源

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
