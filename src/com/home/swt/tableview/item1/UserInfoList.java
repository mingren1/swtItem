package com.home.swt.tableview.item1;

/**
*
* model
*/
public class UserInfoList {
	private String userid;
	private String username;

	public UserInfoList(String userid, String username, int age, String sex, String ardder, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.age = age;
		this.sex = sex;
		this.ardder = ardder;
		this.email = email;
	}

	public UserInfoList() {
	}

	private int age;
	private String sex;
	private String ardder;
	private String email;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getArdder() {
		return ardder;
	}

	public void setArdder(String ardder) {
		this.ardder = ardder;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}