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

@Configuration
@EnableWebMvc
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/helden**").hasAnyRole("USER", "ADMIN").
        antMatchers("**").hasRole("ADMIN")
        .and().httpBasic();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin").roles("ADMIN").build();
    UserDetails user = User.withDefaultPasswordEncoder().username("benutzer").password("123").roles("USER").build();

    return new InMemoryUserDetailsManager(admin, user);
  }
}
