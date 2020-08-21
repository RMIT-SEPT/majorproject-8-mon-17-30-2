package com.rmit.sept.majorProject.security;

import com.rmit.sept.majorProject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
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
        
        //prevent 403 and x-frame errors
        http.csrf().disable();
        http.headers().frameOptions().disable();

        String customer = Person.Role.CUSTOMER.toString();
        String admin = Person.Role.ADMIN.toString();
        String worker = Person.Role.WORKER.toString();
        http.authorizeRequests()
                //browsing
                .antMatchers("/admin").hasRole(admin)
                .antMatchers("/customer").hasAnyRole(admin, customer)
                .antMatchers("/worker").hasRole(worker)
                .antMatchers("/").permitAll()
                //api (temporarily open)
                .antMatchers("/api/customer/register").permitAll()
                .antMatchers("/api/customer").permitAll()
                .antMatchers("/api/worker").permitAll()
                .antMatchers("/api/worker/register").permitAll()
                .antMatchers("/api/admin").permitAll()
                .antMatchers("/api/admin/register").permitAll()
                .antMatchers("/h2-console").permitAll()
                .antMatchers("/api/booking").permitAll()
                .antMatchers("/api/booking/customer").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
//                .and().formLogin();

    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        //Stores passwords as plain text
        return NoOpPasswordEncoder.getInstance();
    }
}