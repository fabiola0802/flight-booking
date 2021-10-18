package com.falterziu.flightdata.security;

import com.falterziu.flightdata.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class UserPrincipal  implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;
   
    private String surname;

    private String username;

    private String email;
    
    @JsonIgnore
    private String password;
   
    private Collection<? extends GrantedAuthority> authorities;
	
   public static UserPrincipal build(UserEntity user) {
	   UserPrincipal userPrincipal = new UserPrincipal();
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        userPrincipal.setAuthorities(grantedAuthorities);
        userPrincipal.setId(user.getId());
        userPrincipal.setEmail(user.getEmail());
        userPrincipal.setPassword(user.getPassword());

        return userPrincipal;
   }
	   
	   


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

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

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}