package com.home.swt.layout.item.comp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.internal.handlers.WizardHandler.New;

public class TopComp {

	private Shell shell;
	
	private boolean unsaved;
	private File file;
	private String lastDirectory;
	
	public TopComp(Shell parent, int style) {
		this.shell = parent;
		//设置快捷选项卡
		Composite shortcutTabComp = new Composite(parent, SWT.NONE);
//		shortcutTabComp.setLayout(new FillLayout(SWT.HORIZONTAL));
		shortcutTabComp.setLayout(new GridLayout(10,false));
		
		GridData shortcutGridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 1);
		shortcutTabComp.setLayoutData(shortcutGridData);
		//创建窗口菜单
	    createMenus(parent);
	    //创建快捷工具栏
		createTools(shortcutTabComp);
		//创建筛选框
		createCombos(shortcutTabComp);
	    
	}

	private void createCombos(Composite shortcutTabComp) {
		//创建下拉框
		Combo combo = new Combo(shortcutTabComp, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.CENTER,SWT.TOP,true,false,2,1));
		for (int i = 0; i < 10; i++) {
			combo.add("第"+i+"项");
		}
		combo.select(0);
	}

	private void createTools(Composite shortcutTabComp) {
		ToolBar mainToolBar = new ToolBar(shortcutTabComp, SWT.SHADOW_OUT);
		mainToolBar.setLayoutData(new GridData(SWT.LEFT,SWT.TOP,true,false,8,1));
		ToolItem toolItem1 = new ToolItem(mainToolBar, SWT.PUSH);
		ImageData imageData = new ImageData("images/Home.png");
		imageData.width = 15;
		imageData.height = 15;
		final Image image = new Image(mainToolBar.getDisplay(), imageData);
//		toolItem1.setImage(image);
		toolItem1.setText("工具1");
		ToolItem toolItem2 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem2.setText("工具2");
		ToolItem toolItem3 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem3.setText("工具3");
	}

	private void createMenus(Shell parent) {
		// 通过shell对象和SWT.BAR样式值来创建一个菜单条
		Menu menu = new Menu(parent, SWT.BAR);
		parent.setMenuBar(menu);
		// 在菜单条的基础之上创建一个File的菜单
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("&文件");
		// 先在Shell上创建一个下拉框，然后将下拉框添加到File菜单上
		Menu filemenu = new Menu(parent, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		// 在下拉框上创建菜单项Open
		final MenuItem openItem = new MenuItem(filemenu, SWT.CASCADE);
		openItem.setText("&打开");
		// 在shell上创建一个下拉框,并把它加到open菜单上
		Menu childmenu = new Menu(parent, SWT.DROP_DOWN);
		openItem.setMenu(childmenu);
		// 在open菜单上创建一个子菜单child
//		final MenuItem childItem = new MenuItem(childmenu, SWT.PUSH);
//		childItem.setText("&Child");
		// 在open菜单上创建一个子菜单dialog
		final MenuItem dialogItem = new MenuItem(childmenu, SWT.PUSH);
		dialogItem.setText("&dialog");
		//增加监听，弹出对话框
		dialogItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loadText();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		// 在菜单项之间创建一个分隔符
		new MenuItem(filemenu, SWT.SEPARATOR);
		// 在下拉框上创建菜单项Exit
		MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("&退出");
		
		// 设置edit菜单
		MenuItem edit = new MenuItem(menu, SWT.CASCADE);
		edit.setText("&退出");
		Menu editmenu = new Menu(parent, SWT.DROP_DOWN);
		edit.setMenu(editmenu);
		MenuItem cutItem = new MenuItem(editmenu, SWT.PUSH);
		cutItem.setText("&剪切\tCTRL+X");
		// 设置快捷键
		cutItem.setAccelerator(SWT.CTRL + 'X');
		// 在菜单项之间创建一个分隔符
		new MenuItem(editmenu, SWT.SEPARATOR);
		MenuItem copyItem = new MenuItem(editmenu, SWT.PUSH);
		copyItem.setText("&复制\tCTRL+C");
		// 设置快捷键
		copyItem.setAccelerator(SWT.CTRL + 'C');
		// 在菜单项之间创建一个分隔符
		new MenuItem(editmenu, SWT.SEPARATOR);
		MenuItem pasteItem = new MenuItem(editmenu, SWT.PUSH);
		pasteItem.setText("&粘贴\tCTRL+V");
		// 设置快捷键
		pasteItem.setAccelerator(SWT.CTRL + 'V');

		// 设置window菜单
		MenuItem window = new MenuItem(menu, SWT.CASCADE);
		window.setText("&Window");
		Menu windowmenu = new Menu(parent, SWT.DROP_DOWN);
		window.setMenu(windowmenu);
		final MenuItem maxItem = new MenuItem(windowmenu, SWT.PUSH);
		maxItem.setText("&Max");
		// 在菜单项之间创建一个分隔符
		new MenuItem(windowmenu, SWT.SEPARATOR);
		MenuItem minItem = new MenuItem(windowmenu, SWT.PUSH);
		minItem.setText("&Min");

		// 设置help菜单
		MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("&帮助");
		Menu helpmenu = new Menu(parent, SWT.DROP_DOWN);
		help.setMenu(helpmenu);
		MenuItem aboutItem = new MenuItem(helpmenu, SWT.PUSH);
		aboutItem.setText("&关于");

		// 添加一个option菜单，用于添加单选菜单和复选菜单
		MenuItem option = new MenuItem(menu, SWT.CASCADE);
		option.setText("&选项");
		Menu optionmenu = new Menu(parent, SWT.DROP_DOWN);
		option.setMenu(optionmenu);
		MenuItem checkItem1 = new MenuItem(optionmenu, SWT.CHECK);
		checkItem1.setText("Check1");
		MenuItem checkItem2 = new MenuItem(optionmenu, SWT.CHECK);
		checkItem2.setText("Check2");
		// 设置默认为选中
		checkItem2.setSelection(true);
		// 设置分隔符
		new MenuItem(optionmenu, SWT.SEPARATOR);
		MenuItem radioItem1 = new MenuItem(optionmenu, SWT.RADIO);
		radioItem1.setText("Radio1");
		// 设置默认为选中
		radioItem1.setSelection(true);
		MenuItem radioItem2 = new MenuItem(optionmenu, SWT.RADIO);
		radioItem2.setText("Radio2");
	}

	protected boolean loadText() {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
//		dialog.open();
		if (lastDirectory != null)
			dialog.setFilterPath(lastDirectory);

		String selectedFile = dialog.open();
		if (selectedFile == null) {
			System.out.println("File is not opened");
			return false;
		}
		file = new File(selectedFile);
		lastDirectory = file.getParent();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
//			styledText.setText(buffer.toString());
			return true;
		} catch (IOException e) {
		}
		return false;
	
		
	}

}
