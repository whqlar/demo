package com.demo.design.listener.multicaster;

import com.demo.design.listener.EventListener;
import com.demo.design.listener.event.Event;

import java.util.Collection;

/**
 * Created by wu on 16/11/22.
 */
public interface Multicaster {

    void addListener(EventListener listener);

    void removeListener(EventListener listener);

    void removeAllListener();

    Collection getAllListener();

    void multicastEvent(Event event);
}
