package com.home.swt.layout.item.model;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PersonContextProvider implements IStructuredContentProvider{
	
	/**
	 *
	 * 在这里对所有集中记录进行处理
	 */
	@Override
	public Object[] getElements(Object element) {
		// 在这里将list转换成数组
		if (element instanceof List) {
			/**
			 * nstanceof是Java的一个二元操作符，和==，>， <是同一类东东。由于它是由字母组成的， 所以也是Java的保留关键字。
			 * 它的作用是测试它左边的对象是否是它右边的类的实例
			 */
			return ((List) element).toArray();// 将list转换成一个数组
		} else {
			// 返回一个空
			return new Object[0];
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
	}




}
