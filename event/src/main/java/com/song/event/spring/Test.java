package com.song.event.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
public class Test {

//      public static void main(String[] args) {
//          AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext( AppConfig.class );
//          IOrderService bean = applicationContext.getBean( OrderServiceImpl.class );
//          bean.pay( "123456" );
//      }

    public static void main(String[] args) {
        IOrderService bean = new AnnotationConfigApplicationContext( AppConfig.class ).getBean( IOrderService.class );
        bean.create( "123456" );
        bean.pay( "123456" );
    }
}
