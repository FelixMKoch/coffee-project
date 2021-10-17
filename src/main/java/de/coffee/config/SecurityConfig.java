package de.coffee.config;

import de.coffee.config.handlers.MyAccessDeniedHandler;
import de.coffee.config.handlers.MyAuthFailedHandler;
import de.coffee.config.handlers.MyLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${user.password}")
    private String password;

    @Value("${user.username}")
    private String username;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .authorizeRequests()
                .antMatchers("/", "/img/**", "/tmpl/**", "/tmpl_plugins/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/logout").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()
                .and().csrf().ignoringAntMatchers("/coffee")
                .and().csrf().ignoringAntMatchers("/shop")
                .and().csrf().ignoringAntMatchers("/review")
                .and().csrf().ignoringAntMatchers("/coffeeinshop")
                ;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.inMemoryAuthentication()
        .withUser(username).password(passwordEncoder().encode(password)).roles("ADMIN");
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new MyLogoutSuccessHandler();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new MyAccessDeniedHandler();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new MyAuthFailedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
