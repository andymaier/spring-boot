package de.predic8.helden.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");

                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests()
                    .antMatchers("/webjars/**","/v2/api-docs*","/swagger**", "/swagger*/**").hasAnyRole("USER","ADMIN")
                    .antMatchers("/helden**").hasAnyRole("USER","ADMIN")
                    .antMatchers("**").hasRole("ADMIN")
                    .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
        // @formatter:on
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin").password("admin").roles("ADMIN")
                        .build();

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("benutzer").password("123").roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin,user);
    }
}
