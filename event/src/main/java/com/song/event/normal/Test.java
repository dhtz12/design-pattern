package com.song.event.normal;

/**
 * packageName:com.song.event.normal
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
public class Test {

    public static void main(String[] args) {
        EventPublisher publisher = new SimpleEventPublisher();
        publisher.registerListener( new SmsEventListener() );
        publisher.registerListener( new EmailEventListener() );
        OrderPayService service = new OrderPayService( publisher );
        service.pay( "123456" );
    }
}
