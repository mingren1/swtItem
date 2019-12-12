package com.home.swt.tableview.item1;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
/**
*
* ITableLabelProvider 会出现4个空函数 我们不需要他 有时候 继承LabelProvider
*/
public class tablepriv extends LabelProvider implements ITableLabelProvider{



	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
