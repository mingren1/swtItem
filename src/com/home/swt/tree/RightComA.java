package com.home.swt.tree;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class RightComA extends Composite{

	public RightComA(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout());
		final Label label = new Label(this, style);
		label.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false));
		label.setText("功能A");
		
		//设置选项卡组件
		final TabFolder tabFolder = new TabFolder(this, style);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("子功能1");
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("子功能2");
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("子功能3");
		
		
	}

}
