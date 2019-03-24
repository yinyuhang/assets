package com.hfnu.assets.other;

import com.hfnu.assets.pojo.Log;
import com.hfnu.assets.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LogRepository logRepository;

    @Override
    @Bean
    public UserService userDetailsService() { //覆盖写userDetailsService方法 (1)
        return new UserService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                /*.antMatchers("/amchart/**",
                        "/bootstrap/**",
                        "/build/**",
                        "/css/**",
                        "/dist/**",
                        "/documentation/**",
                        "/fonts/**",
                        "/js/**",
                        "/pages/**",
                        "/plugins/**"
                ).permitAll() *///默认不拦截静态资源的url pattern （2）
                .anyRequest().authenticated().and()
                .formLogin()// .loginPage("/login")// 登录url请求路径 (3)
                .successHandler(loginSuccess()).permitAll().and() // 登录成功跳转路径url(4)
                .logout().permitAll();

        http.logout().logoutSuccessUrl("/"); // 退出默认跳转页面 (5)

    }

    AuthenticationSuccessHandler loginSuccess() {
        return (request, response, auth) -> {
            String userName = ((User) auth.getPrincipal()).getUsername();
            Optional<com.hfnu.assets.pojo.User> optional = userDetailsService().loadUserByName(userName);
            if (!optional.isPresent()) {
                throw new IOException("User not found" + userName);
            }
            optional.ifPresent(user -> {
                logRepository.save(new Log(null, new Date(), user, Utils.getIpAddress(request)));
                request.getSession().setAttribute("user", user);
            });
            response.sendRedirect("/html/asset.html");
        };
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }
}
