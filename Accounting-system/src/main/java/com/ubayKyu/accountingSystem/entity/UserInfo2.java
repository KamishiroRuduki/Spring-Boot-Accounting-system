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
	  @GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	  @GeneratedValue(generator = "generator")
	  @Column(name="ID" , columnDefinition="uniqueidentifier")
	  private String ID;
	  @Column(name="Account", length=50, nullable=false)
	  private String Account;
	  @Column(name="PWD", length=50, nullable=false)
	  private String PWD;
	  @Column(name="Name", nullable=false, columnDefinition="nvarchar(50)")
	  private String Name;
	  @Column(name="Email", nullable=false, columnDefinition="nvarchar(100)")
	  private String Email;
	  @Column(name="UserLevel", nullable=false)
	  private int UserLevel;
	  @Column(name="CreateDate", length=50, nullable=false, columnDefinition="datetime default getdate()")
	  private LocalDateTime CreateDate;
	  
	  

	  public String getId() {
		return ID;
	  }
	  public void setId(String ID) {
		this.ID = ID;
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
	  public void setCreateDate(LocalDateTime  CreateDate) {
		this.CreateDate = CreateDate;
	  }
	@Override
	public String toString() {
		return "UserInfo2 [ID=" + ID + ", Account=" + Account + ", PWD=" + PWD + ", Name=" + Name + ", Email=" + Email
				+ ", UserLevel=" + UserLevel + ", CreateDate=" + CreateDate + "]";
	}
	  
}
