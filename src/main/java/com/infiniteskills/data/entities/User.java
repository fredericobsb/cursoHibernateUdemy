package com.infiniteskills.data.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="FINANCES_USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private Long userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;
	
	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;
	
	@Column(name="LAST_UPDATE_BY")
	private String lastUpdateBy;
	
	@Column(name="CREATED_DATE", updatable= false)
	private Date createdDate;
	
	@Column(name="CREATED_BY", updatable= false)
	private String createdBy;
	
	@Formula("lower(datediff(curdate(), birth_date)/365)")
	private int age;
	
	/* Aula 35 - OneToOne Bidirecional -> NÃO DEVE pôr @JoinColumn nem Cascade desse lado do 
	relacioonamento, para nao criar um loop infinito de persistencia. */
	@OneToOne(mappedBy="user")
	private Credential credential;
	
	/*
	@Embedded
	@AttributeOverrides({@AttributeOverride(name="addressLine1", column = @Column(name="USER_ADDRESS_LINE1")),
		@AttributeOverride(name="addressLine2", column = @Column(name="USER_ADDRESS_LINE2"))})
	private Address address;
	*/
	
	@ElementCollection
	@CollectionTable(name="USER_ADDRESS", joinColumns = @JoinColumn(name="USER_ID"))
	@AttributeOverrides({@AttributeOverride(name="addressLine1", column = @Column(name="USER_ADDRESS_LINE1")),
		@AttributeOverride(name="addressLine2", column = @Column(name="USER_ADDRESS_LINE2"))})
	private List<Address> address = new ArrayList<Address>();
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}
}
