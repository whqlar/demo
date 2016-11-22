package com.demo.design.listener;

import com.demo.design.listener.event.Event;
import com.demo.design.listener.event.MailEvent;
import org.springframework.stereotype.Service;

/**
 * Created by wu on 16/11/21.
 */
@Service
public class MailEventListener implements EventListener {

    @Override
    public void onEvent(Event event) {
        if (event instanceof MailEvent) {

        }
    }
}
