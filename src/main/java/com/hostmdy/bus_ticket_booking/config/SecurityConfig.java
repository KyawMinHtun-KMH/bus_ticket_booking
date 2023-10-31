package com.hostmdy.bus_ticket_booking.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.hostmdy.bus_ticket_booking.service.impl.UserSecurityService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserSecurityService userSecurityService;
	private final JwtAuthenticationEntryPoint authenticationEntryPoint;
	private final JwtAuthenticationFilter authenticationFilter;
	
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector inspector){
		return new MvcRequestMatcher.Builder(inspector);
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http,MvcRequestMatcher.Builder mvc) throws Exception {
		http.cors((cors) -> cors.configurationSource(corsConfigurationSource()));
		http.csrf((csrf) -> csrf.disable())
			.exceptionHandling((exceptionHandler) -> exceptionHandler.authenticationEntryPoint(authenticationEntryPoint))
			.sessionManagement((sessionManagement) -> 
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.headers((header) -> header.frameOptions((frameOption) -> frameOption.sameOrigin()))
			.securityMatcher("/api/**")
			.authorizeHttpRequests((auth) -> 
				auth
				.requestMatchers(mvc.pattern("/api/ticket/searchticket")).permitAll()
				.requestMatchers(mvc.pattern("/api/ticket/{ticketId:[0-9]+}/ticketSeats")).permitAll()
				.requestMatchers(mvc.pattern("/api/ticket/city")).permitAll()
				.requestMatchers(mvc.pattern("/api/ticket/{ticketId:[0-9]+}")).permitAll()
				.requestMatchers(mvc.pattern("/api/ticket/{ticketId:[0-9]+}/ticketSeats")).permitAll()
				.requestMatchers(mvc.pattern("/api/ticket/create")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/ticket/update")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/ticket/delete/{ticketId:[0-9]+}")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/ticket/all")).hasRole("ADMIN")
				
				.requestMatchers(mvc.pattern("/api/user/signin")).permitAll()
				.requestMatchers(mvc.pattern("/api/user/signup")).permitAll()
				.requestMatchers(mvc.pattern("/api/email/code/confirm")).permitAll()
				
				.requestMatchers(mvc.pattern("/api/route/create")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/route/all")).hasRole("ADMIN")
				
				.requestMatchers(mvc.pattern("/api/bus/create")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/bus/all")).hasRole("ADMIN")
				
				.requestMatchers(mvc.pattern("/api/order/{orderId:[0-9]+}/update")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/order/{orderId:[0-9]+}/delete")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/order/{ticketId:[0-9]+}")).permitAll()
				.requestMatchers(mvc.pattern("/api/order/create/{ticketId:[0-9]+}")).hasRole("USER")
				.requestMatchers(mvc.pattern("/api/order/get")).hasRole("USER")
				.requestMatchers(mvc.pattern("/api/email/reject/{orderId:[0-9]+}")).hasRole("ADMIN")
				.requestMatchers(mvc.pattern("/api/email/confirm/{orderId:[0-9]+}")).hasRole("ADMIN")
				.anyRequest().authenticated()	
			);
			http.authenticationProvider(authProvider());
			http.addFilterBefore(authenticationFilter,UsernamePasswordAuthenticationFilter.class);
			return http.build();	
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	@Bean
	DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userSecurityService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("*"));
	    configuration.setAllowedMethods(Arrays.asList("*"));
	    configuration.setAllowedHeaders(Arrays.asList("*"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
	

}
