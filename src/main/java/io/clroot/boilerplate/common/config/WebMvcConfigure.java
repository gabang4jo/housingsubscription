package io.clroot.boilerplate.common.config;

import io.clroot.boilerplate.common.config.support.SimpleOffsetPageableHandlerMethodArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {

  @Bean
  public SimpleOffsetPageableHandlerMethodArgumentResolver simpleOffsetPageableHandlerMethodArgumentResolver() {
    return new SimpleOffsetPageableHandlerMethodArgumentResolver();
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(simpleOffsetPageableHandlerMethodArgumentResolver());
  }


}