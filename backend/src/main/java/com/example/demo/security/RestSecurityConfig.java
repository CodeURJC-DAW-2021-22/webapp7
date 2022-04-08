package com.example.demo.security;


import com.example.demo.security.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    //Expose AuthenticationManager as a Bean to be used in other services
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.antMatcher("/api/**");

        // URLs that need authentication to access to it
        /*http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/Inicio").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/User/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/User").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/StoredDiets").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/StoredDiets/{id}").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/RemoveDiet").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT,"/api/AddDiet/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/FormDiet").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/Diet/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/AdminProfile").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/RemoveRecipe").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/api/DeleteRecipe").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/AddRecipe").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/FormRecipe").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/FormMenu").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/LogOut").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/StoredRecipes").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/MenuMaker").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/MenuAll").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/FormActiveMenu").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/Menu/{id}").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/Receipt").hasAnyRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/{id}/YourMenu").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/FormRecipe/{id}/image").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/Recipe/{id}/image").hasAnyRole("USER");*/

        // Other URLs can be accessed without authentication
        http.authorizeRequests().anyRequest().permitAll();

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();

        // Disable Http Basic Authentication
        http.httpBasic().disable();

        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add JWT Token filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }
}

