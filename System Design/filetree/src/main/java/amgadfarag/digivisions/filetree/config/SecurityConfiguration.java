package amgadfarag.digivisions.filetree.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .httpBasic();

        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}