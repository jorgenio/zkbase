package org.zkbase.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.UserDetails;

@Entity
@Table(name="zkb_user")
@NamedQueries( {
		@NamedQuery(name = "User.count", query = "SELECT COUNT(u) FROM User u"),
		@NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.username = ?1"),
		@NamedQuery(name = "User.findByNameLike", query = "SELECT u FROM User u WHERE u.username LIKE ?1"),
		@NamedQuery(name = "User.findByExample", query = "SELECT u FROM User u WHERE u.username LIKE ?1 AND u.firstName LIKE ?2"),
		@NamedQuery(name = "User.countByExample", query = "SELECT COUNT(u) FROM User u WHERE u.username LIKE ?1 AND u.firstName LIKE ?2")
		})
public class User implements Serializable, UserDetails {

	private static final long serialVersionUID = -3844753892108734066L;

	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Set<Role> roles = new HashSet<Role>();
	private boolean enabled;
	private boolean accountExpired;
	private boolean accountLocked;
	private boolean credentialsExpired;

	@Id
	@TableGenerator(
			name="userGen",
			table="ID_GEN",
			pkColumnName="GEN_KEY",
			valueColumnName="GEN_VALUE",
			pkColumnValue="USER_ID",
			allocationSize=1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator="userGen")	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isAccountExpired() {
		return accountExpired;
	}

	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	@Transient
	public GrantedAuthority[] getAuthorities() {
		Set<Role> roles = this.getRoles();
		Role[] ga = new Role[roles.size()];

		Iterator<Role> iter = roles.iterator();
		int i = 0;
		while (iter.hasNext())
			ga[i++] = iter.next();
		return ga;
	}

	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return !accountExpired;
	}

	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return !accountLocked;
	}

	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return !credentialsExpired;
	}

}
