package com.rmit.sept.majorProject.security;

import com.rmit.sept.majorProject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/*
    Configure spring security authentication method and url authorisation
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;
    //Configure authentication for application, who is authenticated
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // authorisation using userDetailsService while loads in a user
        // from the database with the given username
        auth.userDetailsService(userDetailsService);

    }

    // configure authorisation, what are they allowed to do
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // configure which routes are accessible to which user depending on role
        // have to connect to front end
        // antmatchers are route patterns
        // matching goes from most restrictive to least restrictive

        String customer = Person.Role.CUSTOMER.toString();
        String admin = Person.Role.ADMIN.toString();
        http.authorizeRequests()
                .antMatchers("/admin").hasRole(admin)
                .antMatchers("/customer").hasAnyRole(admin, customer)
                .antMatchers("/").permitAll()
                .and().formLogin();

    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //Stores passwords as plain text
        return NoOpPasswordEncoder.getInstance();
    }
}

