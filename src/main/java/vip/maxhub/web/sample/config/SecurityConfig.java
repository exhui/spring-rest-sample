package vip.maxhub.web.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import vip.maxhub.web.sample.Constants;
import vip.maxhub.web.sample.security.CustomUserDetailsService;
import vip.maxhub.web.sample.security.EncryptionUtils;
import vip.maxhub.web.sample.security.RestAuthenticationEntryPoint;


/**
 * 多个安全配置
 * Created by jinlei on 2017/4/21.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() //取消跨域限制
            .antMatcher(Constants.URI_API + "/**")
            .authorizeRequests()
            .anyRequest().authenticated()
            .and().httpBasic().authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(new CustomUserDetailsService())
            .passwordEncoder(passwordEncoder());
    }


    @Bean
    public BasePasswordEncoder passwordEncoder() {
        return EncryptionUtils.passwordEncoder();
    }

}
