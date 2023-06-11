package com.song.event.normal;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc:
 */
public interface EventPublisher {

      void registerListener(EventListener listener);

      void removeListener(EventListener listener);

      void notifyListener(AbstractEvent event);
}
