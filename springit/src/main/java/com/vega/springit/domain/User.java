package com.vega.springit.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class User implements UserDetails{

	@Id @GeneratedValue
	private Long id;
	
	@NonNull
	@Column(nullable = false, unique = true)
	@Size(min = 8, max = 20)
	private String email;
	
	@NonNull
	@Column(length = 100)
	private String password;
	
	@NonNull
	@Column(nullable = false)
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
			)
	private Set<Role> roles = new HashSet<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}

	public void addRole(Role role) {
		roles.add(role);
	}

	public void addRoles(Set<Role> roles) {
		this.roles.addAll(roles);
	}
}