package com.royleej9.sample.vo;

import java.sql.Timestamp;

public class User {
	private long seq;
	private String id;
	private String password;
	private String name;
	private Timestamp createdDate;

	public User() {
		super();
	}
	
	public User(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public User(String id, String password, String name, Timestamp createdDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.createdDate = createdDate;
	}



	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [seq=" + seq + ", id=" + id + ", password=" + password + ", name=" + name + ", createdDate=" + createdDate + "]";
	}

}