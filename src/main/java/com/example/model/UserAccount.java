package com.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Entity
@Table(name = "account")
@PropertySource("sample.properties")
@ConfigurationProperties(prefix = "spring")
public class UserAccount {

	@Id
	@GeneratedValue
	public Integer id;

	@NotNull
	public String userName;

	@NotNull
	public String password;
	
	@NotNull
	private boolean enabled = true;

	@NotNull
	private boolean credentialsexpired = false;

	@NotNull
	private boolean expired = false;

	@NotNull
	private boolean locked = false;
	
	@NotNull
	private boolean accountNonLocked = true;
	
	@NotNull
	private boolean accountNonExpired = true;
	
	@NotNull
	private boolean credentialsNonExpired = true;
	
	@Value("{$spring.userPassword}")
	private String userPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "accountrole", joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "role_id"))
	public Set<Role> roles;

	public UserAccount() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isCredentialsexpired() {
		return credentialsexpired;
	}

	public void setCredentialsexpired(boolean credentialsexpired) {
		this.credentialsexpired = credentialsexpired;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Integer getId() {
		return id;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

}
