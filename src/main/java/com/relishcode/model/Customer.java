package com.relishcode.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
	@Id
	@SequenceGenerator(name ="CUSTOMER_SEQ")
	@GeneratedValue(generator = "CUSTOMER_SEQ")
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	@XmlTransient
	private Date createTime;
	@Temporal(TemporalType.TIMESTAMP)
	@XmlTransient
	private Date updateTime;
	
	public Customer() {}
	
	public Customer(int id, String firstName, String lastName, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	
	public long getId() {
		return id;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
		
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@PrePersist
	public void insert() {
		createTime = new Date();
		updateTime = createTime;
	}
	
	@PreUpdate
	public void update() {
		updateTime = new Date();
	}
}
