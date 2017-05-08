package vip.maxhub.web.sample.config;

import org.springframework.context.annotation.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import vip.maxhub.web.sample.Constants;

/**
 * App配置
 * Created by jinlei on 2017/4/18.
 */

@Configuration
@ComponentScan(
    basePackageClasses = {Constants.class},
    excludeFilters = {  //取消以下三个类的注入
        @ComponentScan.Filter(
            type = FilterType.ANNOTATION,
            value = {
                RestController.class,
                ControllerAdvice.class,
                Configuration.class
            }
        )
    }
)

/**
 * 读取配置文件。配置文件读取成功后，将注入到spring的Environment
 */
@PropertySources({
    @PropertySource("classpath:/app.properties"),
    @PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true)
})
public class AppConfig {
}
