package site.metacoding.blogv3._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
////        WebMvcConfigurer.super.addInterceptors(registry);
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/s/**");
//
//        registry.addInterceptor(new LoginApiInterceptor())
//                .addPathPatterns("/a/**");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
//
//        registry
//                .addResourceHandler("/upload/**")
//                .addResourceLocations("file:./upload/")
//                .setCachePeriod(60 * 60) // 초 단위 => 한시간
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver());
//
//    }
}
