package com.song.event.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc:
 */
public class SimpleEventPublisher implements EventPublisher{
    List<EventListener> list = new ArrayList<>();
    @Override
    public void registerListener(EventListener listener) {
        list.add( listener);
    }

    @Override
    public void removeListener(EventListener listener) {
        list.remove(listener);
    }

    @Override
    public  void notifyListener(AbstractEvent event) {
        list.forEach( listener -> listener.onEvent(event) );
    }
}
