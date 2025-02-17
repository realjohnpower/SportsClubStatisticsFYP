package com.example.sportsclubstatisticsfyp.config;

import com.example.sportsclubstatisticsfyp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //Disable CSRF protection for simplicity, consider enabling in production
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        //My publicly accessible endpoints
                        .requestMatchers("/login",
                                "/hash-passwords",
                                "clubEvents/viewClubEvents",
                                "clubEvents/goingToClubEvent/",
                                "clubEvents/clubEventDrillDown"

                        ).permitAll()
                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/clubEvents/addClubEvent").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/clubEvents/addClubEventForm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/teams/addTeam").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/teams/addTeamForm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "clubEvents/editClubEventForm").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "clubEvents/editClubEvent").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/teams/viewTeams").hasAnyRole("TRAINER", "ADMIN", "PLAYER")
                        .requestMatchers(HttpMethod.GET, "/teams/teamDrillDown").hasAnyRole("TRAINER", "ADMIN", "PLAYER")
                        .requestMatchers(HttpMethod.GET, "/teamEvents/viewUsersTeamEvents").hasAnyRole("TRAINER", "PLAYER")

                        .requestMatchers(HttpMethod.GET, "/teamEvents/addTeamEventForm").hasRole("TRAINER")


                        .requestMatchers(HttpMethod.POST, "/teamEvents/addTeamEvent").hasRole("TRAINER")
                        .requestMatchers(HttpMethod.POST, "/teamEvents/editTeamEvent").hasRole("TRAINER")
                        //All other requests require authentication
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/") // go to home page if login is successful.
                        .permitAll()
                );
        //Return the configured HttpSecurity object
        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(encoder())
                .and()
                .build();
    }
}

