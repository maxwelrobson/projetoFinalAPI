package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.demo.security.JwtAuthenticationFilter;
import com.example.demo.security.JwtAuthorizationFilter;
import com.example.demo.security.JwtUtil;



@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Configura o AuthenticationProvider para usar seu UserDetailsService e PasswordEncoder
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/auth/login").permitAll()  // liberado para todos
						.requestMatchers("/public/**").permitAll()
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

								.requestMatchers(HttpMethod.GET, "/perfis/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/perfis/**").hasRole("ADMIN")

								.requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()

								.requestMatchers(HttpMethod.POST, "/contatos/**").permitAll()
								.requestMatchers(HttpMethod.GET, "/contatos/**").hasRole("ADMIN")

								.requestMatchers(HttpMethod.GET, "/categorias/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/categorias/**").permitAll()
								.requestMatchers(HttpMethod.PUT, "/categorias/**").permitAll()

								.requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
								.requestMatchers(HttpMethod.POST, "/produtos/**").permitAll()
								.requestMatchers(HttpMethod.PUT, "/produtos/**").permitAll()

								.requestMatchers(HttpMethod.GET, "/pedidos/**").hasAnyRole("ADMIN", "USER")
								.requestMatchers(HttpMethod.POST, "/pedidos/**").hasAnyRole("ADMIN", "USER")
								.requestMatchers(HttpMethod.PUT, "/pedidos/**").hasRole("ADMIN")

								.requestMatchers(HttpMethod.POST, "/favoritos/**").permitAll()
								.requestMatchers(HttpMethod.GET, "/favoritos/**").permitAll()
								.requestMatchers(HttpMethod.PUT, "/favoritos/**").permitAll()
								.requestMatchers(HttpMethod.DELETE, "/favoritos/**").permitAll()

								.requestMatchers(HttpMethod.POST, "/listadedesejos/**").permitAll()
								.requestMatchers(HttpMethod.GET, "/listadedesejos/**").permitAll()
								.requestMatchers(HttpMethod.PUT, "/listadedesejos/**").permitAll()
								.requestMatchers(HttpMethod.DELETE, "/listadedesejos/**").permitAll()

//						.requestMatchers(HttpMethod.GET, "/perfis/**").permitAll()
//						.requestMatchers(HttpMethod.POST, "/produtos/**").permitAll()
//
//						.requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
//						.requestMatchers(HttpMethod.GET, "/pedidos/**").hasRole("ADMIN")
//
//						.requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
//						.requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()
//						.requestMatchers(HttpMethod.POST, "/pedidos/**").hasRole("USER")


						.requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
						.requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
						.anyRequest().authenticated()
				)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.headers(headers -> headers.frameOptions().disable())

				.authenticationProvider(authenticationProvider()); // registra o authProvider

		http.addFilterBefore(new JwtAuthenticationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
				UsernamePasswordAuthenticationFilter.class);

		http.addFilterBefore(new JwtAuthorizationFilter(
						authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService),
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}