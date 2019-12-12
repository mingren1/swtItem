package com.home.swt.layout.item.model;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private String sex;
	private int age;
	
	
	public Person() {
		super();
	}
	public Person(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}
	
	public List<Person> addList(){
		List<Person> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			list.add(new Person("Ð¡Ã÷", "ÄÐ", 18));
		}
		return list;
	}
	
}
