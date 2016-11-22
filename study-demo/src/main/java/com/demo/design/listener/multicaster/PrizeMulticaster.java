package com.demo.design.listener.multicaster;

import com.demo.design.listener.LogEventListener;
import com.demo.design.listener.MailEventListener;
import com.demo.design.listener.event.Event;
import org.springframework.stereotype.Service;

/**
 * Created by wu on 16/11/22.
 */
@Service
public class PrizeMulticaster extends AbstractMulticaster {

    @Override
    public void multicastEvent(Event event) {
        super.addListener(new MailEventListener());
        super.addListener(new LogEventListener());
        super.multicastEvent(event);
    }
}
