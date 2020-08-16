package com.rmit.sept.majorProject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.rmit.sept.majorProject.model.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*
    UserDetailsImpl uses the user repositories to store user details
    assign properties to them so that spring security can use them
    Is used by UserDetailsServiceImpl to load the appropriate user
 */
public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private SimpleGrantedAuthority role;

    public UserDetailsImpl(Person user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = new SimpleGrantedAuthority(String.format("ROLE_%s", user.getRole().toString()));
    }
    
    public UserDetailsImpl(){
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(role);
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    //Hard coded values for now
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}