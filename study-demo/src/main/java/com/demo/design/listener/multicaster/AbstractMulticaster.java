package com.demo.design.listener.multicaster;

import com.demo.design.listener.EventListener;
import com.demo.design.listener.event.Event;
import com.google.common.collect.Sets;

import java.util.Collection;
import java.util.Set;

/**
 * Created by wu on 16/11/22.
 */
public abstract class AbstractMulticaster implements Multicaster {

    private Set<EventListener> listeners = Sets.newLinkedHashSet();

    @Override
    public void addListener(EventListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void removeAllListener() {
        listeners.clear();
    }

    @Override
    public Collection getAllListener() {
        return listeners;
    }

    @Override
    public void multicastEvent(Event event) {
        for (EventListener listener : listeners) {
            listener.onEvent(event);
        }
    }
}
