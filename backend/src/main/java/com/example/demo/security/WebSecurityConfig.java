package com.example.demo.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RepositoryUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/inicio").permitAll();
        http.authorizeRequests().antMatchers("/Recipe").permitAll();
        http.authorizeRequests().antMatchers("/LogIn").permitAll();
        http.authorizeRequests().antMatchers("/LogInError").permitAll();
        http.authorizeRequests().antMatchers("/SingUpError").permitAll();
        http.authorizeRequests().antMatchers("/User").permitAll();
        http.authorizeRequests().antMatchers("/AboutUs").permitAll();
        http.authorizeRequests().antMatchers("/StoredDiets").permitAll();
        http.authorizeRequests().antMatchers("/diet/*").permitAll();
        http.authorizeRequests().antMatchers("/AdminProfile").permitAll();
        http.authorizeRequests().antMatchers("/RecipeMaker").permitAll();
        http.authorizeRequests().antMatchers("/Recipe/*").permitAll();
        http.authorizeRequests().antMatchers("/Recipes").permitAll();
        http.authorizeRequests().antMatchers("/Recipes/*").permitAll();
        http.authorizeRequests().antMatchers("/processRemoveRecipe").permitAll();
        http.authorizeRequests().antMatchers("/processDeleteRecipe").permitAll();
        http.authorizeRequests().antMatchers("/processAddRecipe").permitAll();
        http.authorizeRequests().antMatchers("/processFormRecipe").permitAll();
        http.authorizeRequests().antMatchers("/processFormMenu").permitAll();
        http.authorizeRequests().antMatchers("/processFormSignUp").permitAll();
        http.authorizeRequests().antMatchers("/processLogOut").permitAll();
        http.authorizeRequests().antMatchers("/processFormLogIn").permitAll();
        http.authorizeRequests().antMatchers("/StoredRecipes").permitAll();
        http.authorizeRequests().antMatchers("/MenuMaker").permitAll();
        http.authorizeRequests().antMatchers("/MenuAll").permitAll();
        http.authorizeRequests().antMatchers("/Menu/*").permitAll();
        http.authorizeRequests().antMatchers("/YourMenu").permitAll();
        http.authorizeRequests().antMatchers("/processFormRecipe/*").permitAll();
        http.authorizeRequests().antMatchers("/recipe/*").permitAll();


        // Private pages
        /*http.authorizeRequests().antMatchers("/newbook").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/editbook/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/removebook/*").hasAnyRole("ADMIN");*/

        // Login form
        http.formLogin().loginPage("/LogIn");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/LogInError");

        // Logout
        /*http.logout().logoutUrl("/processLogOut");
        http.logout().logoutSuccessUrl("/processLogOut");*/
    }
}

