package com.login.app.entity;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user", uniqueConstraints= @UniqueConstraint(columnNames="email"))
public class User {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	//fetch = FetchType.EAGER: Specifies the fetching strategy. When the fetch type is set to EAGER, it means that whenever a User entity is fetched, the associated Role entities will be fetched immediately. 
	//This can lead to performance issues for large collections, so use it only when necessary.
	//cascade = CascadeType.ALL: This means that all persistence operations (such as persist, merge, remove, etc.) done on the User entity will be cascaded to the associated Role entities. For example, if you save a User, the associated Roles will also be saved automatically.
	//@JoinTable: Specifies the join table for the many-to-many relationship. Since JPA doesn’t handle many-to-many relationships with direct foreign keys in either table, a join table is created to manage the relationship between the two entities. This join table will have foreign key columns that reference the User and Role entities.
	//"users_roles" name of the join table, 
	
	  @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	  @JoinTable(name="users_roles", joinColumns=@JoinColumn(name="user_id" ,referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName="id")) 
	  private Collection<Role> roles;
	 
	
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) 
	{
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	
	public User() {
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Collection<Role> getRoles() {
		return roles; 
		} 
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
		}

	

}
