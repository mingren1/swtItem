package com.home.swt.example;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class SwtExap2 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtExap2 window = new SwtExap2();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 216, 228);
		composite.setLayout(null);
		
		Tree tree = new Tree(composite, SWT.BORDER);
		tree.setBounds(36, 28, 135, 198);
		TreeItem treeItem1 = new TreeItem(tree, SWT.SINGLE);
		treeItem1.setText("第1棵树");
		TreeItem treeItem2 = new TreeItem(tree, SWT.SINGLE);
		treeItem2.setText("第2棵树");
		TreeItem treeItem11= new TreeItem(treeItem1, SWT.SINGLE);
		treeItem11.setText("第11棵树");
		TreeItem treeItem21= new TreeItem(treeItem2, SWT.SINGLE);
		treeItem21.setText("第21棵树");
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem menuItem1 = new MenuItem(menu, SWT.CASCADE);
		menuItem1.setText("\u6587\u4EF6");
		Menu menu1 = new Menu(shell, SWT.DROP_DOWN);
		menuItem1.setMenu(menu1);
		MenuItem menuItem11 = new MenuItem(menu1, SWT.NONE);
		menuItem11.setText("文件11");
		
		
		MenuItem menuItem2 = new MenuItem(menu, SWT.NONE);
		menuItem2.setText("\u7F16\u8F91");
		
		MenuItem menuItem3 = new MenuItem(menu, SWT.NONE);
		menuItem3.setText("\u5DE5\u5177");
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(252, 44, 170, 174);
		tabFolder.setToolTipText("aa");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("选项卡1");
		tbtmNewItem.setData("我是选项卡1data");
		
		Label lblNewLabel = new Label(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(lblNewLabel);
		lblNewLabel.setText("New Label");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("选项卡2");
		tbtmNewItem_1.setData("我是选项卡1data");

	}
}
