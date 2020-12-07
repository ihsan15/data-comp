package com.ihsan.playermarket;

import com.ihsan.playermarket.config.PlayerMarketConfig;
import com.ihsan.playermarket.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PlayerMarketConfig.class, SwaggerConfig.class})
public class PlayerMarketApplication {
    public static void main(String[] args) {
        SpringApplication.run(PlayerMarketApplication.class, args);
    }

}
