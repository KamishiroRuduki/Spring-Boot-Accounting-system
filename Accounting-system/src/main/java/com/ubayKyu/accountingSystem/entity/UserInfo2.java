package com.ubayKyu.accountingSystem.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "UserInfo")
public class UserInfo2 {
	@Id
	@Column(name = "ID", columnDefinition = "uniqueidentifier")
	private String ID;
	@Column(name = "Account", length = 50, nullable = false)
	private String Account;
	@Column(name = "PWD", length = 50, nullable = false)
	private String PWD;
	@Column(name = "Name", nullable = false, columnDefinition = "nvarchar(50)")
	private String Name;
	@Column(name = "Email", nullable = false, columnDefinition = "nvarchar(100)")
	private String Email;
	@Column(name = "UserLevel", nullable = false)
	private int UserLevel;
	@Column(name = "CreateDate", length = 50, nullable = false, columnDefinition = "datetime default getdate()")
	private LocalDateTime CreateDate;
	@Column(name = "EditDate", nullable = true, columnDefinition = "datetime")
	private LocalDateTime EditDate;

	public String getId() {
		return ID;
	}

	public void setId(String ID) {
		this.ID = ID;
	}

	public LocalDateTime getEditDate() {
		return EditDate;
	}

	public void setEditDate(LocalDateTime editDate) {
		EditDate = editDate;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String Account) {
		this.Account = Account;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		this.Email = email;
	}

	public String getPWD() {
		return PWD;
	}

	public void setPWD(String PWD) {
		this.PWD = PWD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public int getUserLevel() {
		return UserLevel;
	}

	public void setUserLevel(int UserLevel) {
		this.UserLevel = UserLevel;
	}

	public LocalDateTime getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(LocalDateTime CreateDate) {
		this.CreateDate = CreateDate;
	}

}
