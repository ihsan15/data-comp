package com.ihsan.datacomp;

import com.ihsan.datacomp.config.DataCompConfig;
import com.ihsan.datacomp.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DataCompConfig.class, SwaggerConfig.class})
public class DataCompApplication {
    public static void main(String[] args) {
        SpringApplication.run(DataCompApplication.class, args);
    }

}
