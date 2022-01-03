package com.wsy.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wsy.yygh.hosp.mapper")
public class HospitalConfig {
}
