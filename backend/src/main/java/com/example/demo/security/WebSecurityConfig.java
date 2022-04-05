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
        http.authorizeRequests().antMatchers("/LogIn").permitAll();
        http.authorizeRequests().antMatchers("/LogInError").permitAll();
        http.authorizeRequests().antMatchers("/SingUpError").permitAll();
        http.authorizeRequests().antMatchers("/AboutUs").permitAll();
        http.authorizeRequests().antMatchers("/Recipe/*").permitAll();
        http.authorizeRequests().antMatchers("/Recipes").permitAll();
        http.authorizeRequests().antMatchers("/Recipes/*").permitAll();
        http.authorizeRequests().antMatchers("/processFormSignUp").permitAll();
        http.authorizeRequests().antMatchers("/processLogOut").permitAll();
        http.authorizeRequests().antMatchers("/processFormLogIn").permitAll();
        http.authorizeRequests().antMatchers("/recipe/*").permitAll();

        // Private pages
        http.authorizeRequests().antMatchers("/User").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/StoredDiets").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/diet/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/StoredRecipes").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/MenuMaker").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/MenuAll").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/Menu/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/AdminProfile").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers("/DietMaker").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processFormDiet").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processAddDiet/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processRemoveDiet/*").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/RecipeMaker").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/YourMenu").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processFormRecipe/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/processFormMenu").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processRemoveRecipe").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processDeleteRecipe").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/processAddRecipe").hasAnyRole("USER");
        http.authorizeRequests().antMatchers("/processFormRecipe").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/processUpdateRecipe/*").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/RecipeUpdater/*").hasAnyRole("ADMIN");




        // Login form
        http.formLogin().loginPage("/processFormLogIn");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/");
        http.formLogin().failureUrl("/LogInError");

    }
}

