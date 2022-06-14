package com.practica.config.springdoc;

import com.practica.utils.converter.TicketToCtrlTicketRspConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TicketToCtrlTicketRspConverter());
    }
}
