package com.macademy.recordmgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                // 🔒 Disables CSRF protection (OK for development or APIs)
                // CSRF is security for forms — disabling it means easier testing but less protection

                .authorizeRequests()
                // 📋 Start defining which URLs are public or protected

                .antMatchers("/registration**", "/js/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                // 🌐 Allow access to:
                // - "/registration" pages (e.g. /registration, /registration-form)
                // - All static resources like JS, CSS, images, and libraries
                // No login is needed to access these paths

                .anyRequest().permitAll()
                // ✅ Allow every other request (any URL) without login
                // This basically means: "my site is open to all for now"

                .and().formLogin()
                // 🧾 Enable form-based login (shows login page when login is required)

                .loginPage("/login").permitAll()
                // 📥 Use a custom login page (must create your own "/login" endpoint)
                // Allow everyone to access it

                .defaultSuccessUrl("/")
                // 🎯 After login is successful, redirect user to home page ("/")

                .and().logout()
                // 🚪 Begin logout settings

                .invalidateHttpSession(true)
                // 🧹 Clears all session data when logging out

                .clearAuthentication(true)
                // 🚫 Clears authentication details when logging out

                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // 🔄 Set the logout URL to "/logout"
                // Now, visiting "/logout" will trigger logout

                .logoutSuccessUrl("/")
                // 🏠 After logout, redirect to home page

                .permitAll();
        // ✅ Let everyone access the logout URL

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}