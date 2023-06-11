package com.song.event.normal;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/29
 * desc:
 */
public class EmailEventListener implements EventListener {

    @Override
    public void onEvent(AbstractEvent event) {
        System.out.println("邮件发送成功");
    }
}
