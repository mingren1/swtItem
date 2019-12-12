package com.home.swt.tree.item.model;

import java.util.ArrayList;
import java.util.List;

public class Country implements Itree<City>{
	private Long id;
	private String name;
	private List<City> children = new ArrayList<City>();
	public Country(){
	}
	public Country(String name){
		this.name = name;
	}
	public List<City> getChildren() {
		return children;
	}
	public void setChildren(List<City> children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
