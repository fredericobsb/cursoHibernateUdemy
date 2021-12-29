package com.infiniteskills.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="CREDENTIAL")
public class Credential {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long credentialId;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	//aula34 -> OneToOne unidirecional - NÃO é possivel acessar a credencial a partir do usuario: não mapeou nada na entidade usuario, só aqui.
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
