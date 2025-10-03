package com.backoven.catdogshelter.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/* 설명. AOP를 위한 @Aspect 클래스를 Bean으로 등록화기 위한 설정 */

@Configuration
@ComponentScan(basePackages = "com.backoven.catdogshelter.common.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AOPConfiguration {
}
