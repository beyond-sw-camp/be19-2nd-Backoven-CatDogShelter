package com.backoven.catdogshelter.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 설명. Entity <-> DTO 매핑을 위한 객체 ModelMapper를 Bean으로 등록하기 위한 설정 */

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
