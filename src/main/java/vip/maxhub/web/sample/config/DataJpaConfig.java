package vip.maxhub.web.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vip.maxhub.web.sample.Constants;

/**
 * Created by jinlei on 2017/4/20.
 */
@Configuration
@EnableJpaRepositories(basePackages = {Constants.BASE_PACKAGE})
public class DataJpaConfig {


}
