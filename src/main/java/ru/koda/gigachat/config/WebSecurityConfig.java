package ru.koda.gigachat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import ru.koda.gigachat.security.JwtRequestFilter;

import java.util.Collections;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] ALLOWED_GUEST_POST = new String[] { "/api/login", "/api/register", "/chat" };

    private final UserDetailsService userDetailsService;

    private final JwtRequestFilter jwtRequestFilter;

    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl") final UserDetailsService userDetailsService,
            final JwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Collections.singletonList(CorsConfiguration.ALL));
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("http://localhost:*"));
        corsConfiguration.setAllowCredentials(true);

        httpSecurity.cors().configurationSource(request -> corsConfiguration.applyPermitDefaultValues());

        httpSecurity.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, ALLOWED_GUEST_POST)
                .permitAll()
                .antMatchers(HttpMethod.POST, ALLOWED_GUEST_POST)
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}