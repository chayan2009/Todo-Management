package com.todo.todoapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf().disable().authorizeHttpRequests((autharize) -> {
            // autharize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
            // autharize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
            // autharize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER","MANAGER");
            // autharize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
            // autharize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER","MANAGER");
            // autharize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
            autharize.anyRequest().authenticated();
        }).httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
	public UserDetailsService userDetailsService(){

		UserDetails root=org.springframework.security.core.userdetails.User.builder().username("root").password(passwordEncoder().encode("root")).roles("USER").build();

		UserDetails admin=org.springframework.security.core.userdetails.User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();

		return new InMemoryUserDetailsManager(root,admin);

	}

	@Bean
	public static PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
