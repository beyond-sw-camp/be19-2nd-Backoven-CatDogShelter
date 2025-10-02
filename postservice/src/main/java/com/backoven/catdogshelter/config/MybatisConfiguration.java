package com.backoven.catdogshelter.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/* 설명. Mapper를 Bean으로 등록하기 위한 설정 */

@Configuration
@MapperScan(basePackages = "com.backoven.catdogshelter", annotationClass = Mapper.class)
public class MybatisConfiguration { }
