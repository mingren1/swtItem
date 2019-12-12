package com.home.swt.layout.item;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import com.home.swt.layout.item.comp.LeftComp;
import com.home.swt.layout.item.comp.RightComp;
import com.home.swt.layout.item.comp.TopComp;

public class SwtLayoutExap333 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtLayoutExap333 window = new SwtLayoutExap333();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.j
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents(display);
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
	protected void createContents(Display display) {
		shell = new Shell();
		shell.setSize(1000, 500);
		shell.setText("SWT 主窗口");
		shell.setLayout(new GridLayout(5,false));
		TopComp topComp = new TopComp(shell, SWT.NONE);
		
		LeftComp leftComp = new LeftComp(shell,SWT.NONE);
		Tree tree = leftComp.getTree();
		
		RightComp rightComp = new RightComp(shell);
		
		//增加树的子节点的监听事件
		rightComp.setTreeListener(tree);

	}
		
}

