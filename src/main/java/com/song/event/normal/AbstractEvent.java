package com.song.event.normal;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc: 抽象事件类
 */
public abstract class AbstractEvent {

    private Object object;

    public AbstractEvent(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void notifyEvent() {

    }
}
