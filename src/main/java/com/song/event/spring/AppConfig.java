package com.song.event.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * packageName:com.song.event.spring
 * User: lss
 * Date: 2023/4/30
 * desc:
 */
@Configuration
@ComponentScan("com.song.event.spring")
@EnableAsync
public class AppConfig {


}
