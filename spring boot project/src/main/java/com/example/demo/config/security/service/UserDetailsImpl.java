package com.example.demo.config.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.entity.StpUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailsImpl {
    private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private StpUser stpUser;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String username, String password, StpUser StpUser,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.stpUser = StpUser;
		this.authorities = authorities;
	}

	public UserDetailsImpl(String username, String password, boolean accountNonExpired,
			boolean accountNonLocked, boolean credentialsNonExpired,
			boolean enabled,
			StpUser StpUser, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.stpUser = StpUser;
		this.authorities = authorities;
	}

	
	public static UserDetailsImpl build(StpUser user) {
		List<GrantedAuthority> authorities =new ArrayList<>();

		return new UserDetailsImpl(
				user.getUsername(),
				user.getPassword(),
				true,
				true,
				true,
				true,
				user,
				authorities);
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Return the user's authorities/roles here
		return authorities;
	}
}
