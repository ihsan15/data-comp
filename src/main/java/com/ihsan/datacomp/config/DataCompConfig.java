package com.ihsan.datacomp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.ihsan.datacomp", excludeFilters = {@ComponentScan.Filter(Configuration.class)})
public class DataCompConfig {
}
