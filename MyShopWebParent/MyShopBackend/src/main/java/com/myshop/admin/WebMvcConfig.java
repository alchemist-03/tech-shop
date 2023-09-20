package com.myshop.admin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        expose("users-photo",registry);
        expose("../categories-photo",registry);
        expose("brands-logo",registry);

    }

    public void expose(String dirName,ResourceHandlerRegistry registry) {
        Path dirPath = Paths.get(dirName);

        String pathStr = dirPath.toAbsolutePath().toString();

        dirName = dirName.replace("../","");
        registry.addResourceHandler("/" + dirName + "/**")
                .addResourceLocations("file:/" + pathStr + "/");
    }
}
