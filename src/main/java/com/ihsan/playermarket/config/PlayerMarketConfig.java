package com.ihsan.playermarket.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.ihsan.playermarket", excludeFilters = {@ComponentScan.Filter(Configuration.class)})
@EntityScan("com.ihsan.playermarket.entity")
@EnableJpaRepositories("com.ihsan.playermarket.repository")
public class PlayerMarketConfig {
}
