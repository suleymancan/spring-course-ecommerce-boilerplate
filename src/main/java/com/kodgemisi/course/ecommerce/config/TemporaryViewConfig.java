package com.kodgemisi.course.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//incele
@Configuration
class TemporaryViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("dashboard/index");
        registry.addViewController("/login").setViewName("login");

    }

    //farkli view tipleri icin kullandik. incele
    //xml donus tipi icin incele
    //incele
    //    @Override
    //    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //        configurer.
    //                // this won't work without adding @EnableWebMvc annotation to this class
    //                // but you will lose default configuration of spring boot by adding this annotation
    //                favorPathExtension(true).
    //                ignoreAcceptHeader(true).
    //                useRegisteredExtensionsOnly(true).
    //                defaultContentType(MediaType.APPLICATION_JSON);
    //    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                favorPathExtension(true).
                ignoreAcceptHeader(true).
                useRegisteredExtensionsOnly(true).
                defaultContentType(MediaType.APPLICATION_JSON);
    }
    //    @Override
    //    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
    //        configurer.
    //                favorParameter(true).
    //                favorPathExtension(false).
    //                ignoreAcceptHeader(true).
    //                useRegisteredExtensionsOnly(true).
    //                defaultContentType(MediaType.APPLICATION_JSON);
    //    }

}
