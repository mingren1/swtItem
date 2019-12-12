package com.home.swt.tableview.item1;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
/**
 * 标签器 （对单行记录进行处理）
 * 
 * @author Administrator
 *
 */
public class tableLableProvider implements ITableLabelProvider{
	


	@Override
	public Image getColumnImage(Object element, int index) {
		// Image image=new Image(device, data)
		// return ;
		return null;
	}

	/**
	 * 这个方法返回的是各列的记录的文字 参数1:输入的对象 参数2:列号 返回值:注意一定要避免Null值,否则出错
	 */
	@Override
	public String getColumnText(Object element, int index) {
		switch (index) {
		case 0:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getUserid();
			} else {
				return "--";
			}
		case 1:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getUsername();
			} else {
				return "--";
			}
		case 2:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getSex();
			} else {
				return "--";
			}
		case 3:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getAge() + "";
			} else {
				return "--";
			}
		case 4:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getArdder();
			} else {
				return "--";
			}
		case 5:
			if (element instanceof UserInfoList) {
				UserInfoList user = (UserInfoList) element;
				return user.getEmail();
			} else {
				return "--";
			}
		}
		return "";
	}

	//
	// UserInfoList user=(UserInfoList)element;
	//
	// switch(index){
	//
	// case 0:
	//
	// user.getUserid();
	//
	// case 1:
	// return user.getUsername();
	//
	//
	// case 2:
	//
	// return user.getSex();
	//
	//
	// case 3:
	// return user.getAge()+"";
	//
	//
	// case 4:
	//
	// return user.getArdder();
	//
	//
	// case 5:
	//
	// return user.getEmail();
	//
	// }
	// return "";
	// }
	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
	}


}
