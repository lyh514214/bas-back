package com.ahao.admin.config;

import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 排除url中非法字符
 * @Author: ahao
 * @Date: 2023/4/19 14:33
 **/

@Configuration
public class UrlCharacterConfig {

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector
                -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }

}
