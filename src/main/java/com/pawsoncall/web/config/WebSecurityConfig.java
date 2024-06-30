package com.pawsoncall.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import com.pawsoncall.web.mapper.OAuth2UserService;
import com.pawsoncall.web.mapper.UserService;
import com.pawsoncall.web.service.UsrDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import org.springframework.security.oauth2.core.user.OAuth2User;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UsrDetailsService userDetailsService;
    @Autowired
    private OAuth2UserService oauth2UserService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
                    antMatcher("/webjars/**"),
                    // bypass login and register
                    antMatcher("/login"),
                    antMatcher("/register")
                    // end bypass
                    ).permitAll()
				.anyRequest().authenticated()
			)
            .csrf(c -> c
                .csrfTokenRepository(tokenRepository)
                .csrfTokenRequestHandler(requestHandler)
            )
            .logout(l -> l
				.logoutSuccessUrl("/").permitAll()
			)
            .httpBasic(Customizer.withDefaults())
            .formLogin((form) -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true) // todo redirect to user prior visited page
                .permitAll()
            )
            .oauth2Login(o -> o
                .loginPage("/login")
                .failureHandler((request, response, exception) -> {
                    request.getSession().setAttribute("error.message", exception.getMessage());
                    handler.onAuthenticationFailure(request, response, exception);
                })
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                            jakarta.servlet.http.HttpServletResponse response,
                            org.springframework.security.core.Authentication authentication) throws IOException,
                            ServletException {
                        // check if we need to persistent in User table
                        OAuth2User oauth2User = (OAuth2User)authentication.getPrincipal();
                        String curEmail = oauth2User.<String>getAttribute("email");
                        String curFirstName = oauth2User.<String>getAttribute("given_name");
                        String curLastName = oauth2User.<String>getAttribute("family_name");
                        oauth2UserService.onAuthenticationSuccess(curEmail, curFirstName, curLastName);
                        response.sendRedirect("/home");
                    }
                })
            )
            ;
		// @formatter:on
        return http.build();
    }

    // @Bean
    // public AuthenticationEntryPoint authenticationEntryPoint() {
    // return new AuthEntryPoint();
    // }


    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    // }
}
