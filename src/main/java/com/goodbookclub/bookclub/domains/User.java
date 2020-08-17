package com.goodbookclub.bookclub.domains;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractDomainClass{
	
	private String username;
	@Transient
	private String password;
	private String encryptedpassword;
	private boolean enabled;
	private String role;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Customer customer;
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
		customer.setUser(this);
	}

}
