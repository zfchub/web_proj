package pers.husen.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * user: zhoufangchao
 * date: 2018/9/27
 */
@Configuration
@EnableWebMvc
@ComponentScan("pers.husen.web")
public class WebMvcConfig implements WebMvcConfigurer {

    // 请求静态资源的额外处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/css", "/images", "/js", "/module", "/personal_center", "/plugins", "/topic")
                .setCachePeriod(31556926);
    }
}
