package com.home.swt.tableview.item1;


import java.util.ArrayList;
import java.util.List;


public class Data {
	

	public List addlist() {
		List list = new ArrayList<UserInfoList>();
		for (int i = 0; i < 5; i++) {
			UserInfoList user = new UserInfoList("1", "����", 12, "Ů", "����", "luo@163.com");
			list.add(user);
		}
		return list;
	}



}
