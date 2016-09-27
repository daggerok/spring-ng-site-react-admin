package daggerok.config;

import daggerok.admin.AdminUserDetailsService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${management.context-path}")
    String contextPath;

    @Autowired
    AdminUserDetailsService adminUserDetailsService;

    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        // @formatter:off
        http
            .authorizeRequests()
                .antMatchers("/", "/client.*", "/client.css", "/vendor/**").permitAll()
                .antMatchers("/admin**").hasAuthority("SUPERADMIN")
                .anyRequest().fullyAuthenticated()
            .and()
                .formLogin()
            .and()
                .csrf().disable();
        // @formatter:on
    }

    @Override
    public UserDetailsService userDetailsServiceBean() {
        return adminUserDetailsService;
    }

    @Override
    @Autowired
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {

        // @formatter:off
        auth // in memory hash map:
            .inMemoryAuthentication()
                .withUser("admin")
                    .password("admin")
                    .roles("USER", "ADMIN")
                    .authorities(AuthorityUtils.createAuthorityList("USER", "ADMIN"))
                    .and()
                .and()
            // mongo db:
            .userDetailsService(userDetailsServiceBean())
                .passwordEncoder(new BCryptPasswordEncoder());
        // @formatter:on
    }
}
