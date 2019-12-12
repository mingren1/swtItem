package com.home.swt.tree.item.model;

import java.util.ArrayList;
import java.util.List;

public class City implements Itree<People>{
	private Long id;
	private String name;
	private List<People> children = new ArrayList<People>();
	public City(){
	}
	public City(String name){
		this.name = name;
	}
	public List<People> getChildren() {
		return children;
	}
	public void setChildren(List<People> children) {
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
