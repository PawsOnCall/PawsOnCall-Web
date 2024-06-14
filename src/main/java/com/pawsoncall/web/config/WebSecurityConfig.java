package com.pawsoncall.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // borrow from
        // https://docs.spring.io/spring-security/reference/5.8/migration/servlet/exploits.html#_i_am_using_angularjs_or_another_javascript_framework
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName(null);

        SimpleUrlAuthenticationFailureHandler handler =
                new SimpleUrlAuthenticationFailureHandler("/");

        // @formatter:off
		http
			.authorizeHttpRequests(a -> a
				.requestMatchers(
                    // bypass swagger start
                    antMatcher("/v3/**"), 
                    antMatcher("/swagger-ui/**"),
                    antMatcher("/api/**"),
                    // bypass swagger end
                    antMatcher("/"), 
                    antMatcher("/error"),
                    antMatcher("/webjars/**")).permitAll()
				.anyRequest().authenticated()
			)
            .csrf(c -> c
                .csrfTokenRepository(tokenRepository)
                .csrfTokenRequestHandler(requestHandler)
            )
            .logout(l -> l
				.logoutSuccessUrl("/").permitAll()
			)
            .oauth2Login(o -> o
                .failureHandler((request, response, exception) -> {
			    request.getSession().setAttribute("error.message", exception.getMessage());
			    handler.onAuthenticationFailure(request, response, exception);
            })
            );
		// @formatter:on
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthEntryPoint();
    }
}
