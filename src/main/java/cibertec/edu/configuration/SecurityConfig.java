package cibertec.edu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**").permitAll()
		//.antMatchers("/registro").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
		//.loginPage("/login")
		.permitAll()
		.and()
		.logout().permitAll();
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails admin = User.builder()
				.username("turpo")
				.password(passwordEncoder().encode("turpocl3"))
				.roles("ADMIN")
				.build();
		UserDetails user = User.builder()
				.username("corayma")
				.password(passwordEncoder().encode("coraymacl3"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user,admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
