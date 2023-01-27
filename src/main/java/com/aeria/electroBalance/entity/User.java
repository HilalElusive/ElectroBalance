package com.aeria.electroBalance.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name="user_generator", sequenceName = "user_seq")
    @Column(name = "user_id")
    private Long id;
    
    @Column(name = "username",nullable = false, unique = true,length = 10)
    private String userName;
    
    @Column(name = "password",nullable = false)
    private String password;
    
    @Column(name = "active")
    private Boolean active;
    
    @Column(name = "user_role")
    private String role;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Facture> factures = new ArrayList<>();

	public User(String userName, String password, Boolean active, String role) {
		this.userName = userName;
		this.password = password;
		this.active = active;
		this.role = role;
	}
	
	public List<Facture> getFactures() {
		return factures;
	}
	
	public void setFactures(List<Facture> factures) {
		this.factures = factures;
	}
	
	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", active=" + active + ", role="
				+ role + "]";
	}
 
    

}