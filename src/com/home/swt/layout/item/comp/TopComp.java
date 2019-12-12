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
		//���ÿ��ѡ�
		Composite shortcutTabComp = new Composite(parent, SWT.NONE);
//		shortcutTabComp.setLayout(new FillLayout(SWT.HORIZONTAL));
		shortcutTabComp.setLayout(new GridLayout(10,false));
		
		GridData shortcutGridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 1);
		shortcutTabComp.setLayoutData(shortcutGridData);
		//�������ڲ˵�
	    createMenus(parent);
	    //������ݹ�����
		createTools(shortcutTabComp);
		//����ɸѡ��
		createCombos(shortcutTabComp);
	    
	}

	private void createCombos(Composite shortcutTabComp) {
		//����������
		Combo combo = new Combo(shortcutTabComp, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.CENTER,SWT.TOP,true,false,2,1));
		for (int i = 0; i < 10; i++) {
			combo.add("��"+i+"��");
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
		toolItem1.setText("����1");
		ToolItem toolItem2 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem2.setText("����2");
		ToolItem toolItem3 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem3.setText("����3");
	}

	private void createMenus(Shell parent) {
		// ͨ��shell�����SWT.BAR��ʽֵ������һ���˵���
		Menu menu = new Menu(parent, SWT.BAR);
		parent.setMenuBar(menu);
		// �ڲ˵����Ļ���֮�ϴ���һ��File�Ĳ˵�
		MenuItem file = new MenuItem(menu, SWT.CASCADE);
		file.setText("&�ļ�");
		// ����Shell�ϴ���һ��������Ȼ����������ӵ�File�˵���
		Menu filemenu = new Menu(parent, SWT.DROP_DOWN);
		file.setMenu(filemenu);
		// ���������ϴ����˵���Open
		final MenuItem openItem = new MenuItem(filemenu, SWT.CASCADE);
		openItem.setText("&��");
		// ��shell�ϴ���һ��������,�������ӵ�open�˵���
		Menu childmenu = new Menu(parent, SWT.DROP_DOWN);
		openItem.setMenu(childmenu);
		// ��open�˵��ϴ���һ���Ӳ˵�child
//		final MenuItem childItem = new MenuItem(childmenu, SWT.PUSH);
//		childItem.setText("&Child");
		// ��open�˵��ϴ���һ���Ӳ˵�dialog
		final MenuItem dialogItem = new MenuItem(childmenu, SWT.PUSH);
		dialogItem.setText("&dialog");
		//���Ӽ����������Ի���
		dialogItem.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				loadText();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		// �ڲ˵���֮�䴴��һ���ָ���
		new MenuItem(filemenu, SWT.SEPARATOR);
		// ���������ϴ����˵���Exit
		MenuItem exitItem = new MenuItem(filemenu, SWT.PUSH);
		exitItem.setText("&�˳�");
		
		// ����edit�˵�
		MenuItem edit = new MenuItem(menu, SWT.CASCADE);
		edit.setText("&�˳�");
		Menu editmenu = new Menu(parent, SWT.DROP_DOWN);
		edit.setMenu(editmenu);
		MenuItem cutItem = new MenuItem(editmenu, SWT.PUSH);
		cutItem.setText("&����\tCTRL+X");
		// ���ÿ�ݼ�
		cutItem.setAccelerator(SWT.CTRL + 'X');
		// �ڲ˵���֮�䴴��һ���ָ���
		new MenuItem(editmenu, SWT.SEPARATOR);
		MenuItem copyItem = new MenuItem(editmenu, SWT.PUSH);
		copyItem.setText("&����\tCTRL+C");
		// ���ÿ�ݼ�
		copyItem.setAccelerator(SWT.CTRL + 'C');
		// �ڲ˵���֮�䴴��һ���ָ���
		new MenuItem(editmenu, SWT.SEPARATOR);
		MenuItem pasteItem = new MenuItem(editmenu, SWT.PUSH);
		pasteItem.setText("&ճ��\tCTRL+V");
		// ���ÿ�ݼ�
		pasteItem.setAccelerator(SWT.CTRL + 'V');

		// ����window�˵�
		MenuItem window = new MenuItem(menu, SWT.CASCADE);
		window.setText("&Window");
		Menu windowmenu = new Menu(parent, SWT.DROP_DOWN);
		window.setMenu(windowmenu);
		final MenuItem maxItem = new MenuItem(windowmenu, SWT.PUSH);
		maxItem.setText("&Max");
		// �ڲ˵���֮�䴴��һ���ָ���
		new MenuItem(windowmenu, SWT.SEPARATOR);
		MenuItem minItem = new MenuItem(windowmenu, SWT.PUSH);
		minItem.setText("&Min");

		// ����help�˵�
		MenuItem help = new MenuItem(menu, SWT.CASCADE);
		help.setText("&����");
		Menu helpmenu = new Menu(parent, SWT.DROP_DOWN);
		help.setMenu(helpmenu);
		MenuItem aboutItem = new MenuItem(helpmenu, SWT.PUSH);
		aboutItem.setText("&����");

		// ���һ��option�˵���������ӵ�ѡ�˵��͸�ѡ�˵�
		MenuItem option = new MenuItem(menu, SWT.CASCADE);
		option.setText("&ѡ��");
		Menu optionmenu = new Menu(parent, SWT.DROP_DOWN);
		option.setMenu(optionmenu);
		MenuItem checkItem1 = new MenuItem(optionmenu, SWT.CHECK);
		checkItem1.setText("Check1");
		MenuItem checkItem2 = new MenuItem(optionmenu, SWT.CHECK);
		checkItem2.setText("Check2");
		// ����Ĭ��Ϊѡ��
		checkItem2.setSelection(true);
		// ���÷ָ���
		new MenuItem(optionmenu, SWT.SEPARATOR);
		MenuItem radioItem1 = new MenuItem(optionmenu, SWT.RADIO);
		radioItem1.setText("Radio1");
		// ����Ĭ��Ϊѡ��
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
