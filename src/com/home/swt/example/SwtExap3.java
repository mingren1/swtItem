package com.home.swt.example;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class SwtExap3 {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtExap3 window = new SwtExap3();
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
//		shell.setSize(610, 386);
		shell.setText("主窗口");
		FillLayout fl_shell = new FillLayout();
		fl_shell.type = SWT.VERTICAL;
		shell.setLayout(fl_shell);
		
		ViewForm viewForm = new ViewForm(shell, SWT.NONE);
		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT);
		viewForm.setContent(toolBar);
		
		ToolItem add = new ToolItem(toolBar, SWT.NONE);
		add.setText("添加");
		
		ToolItem del = new ToolItem(toolBar, SWT.NONE);
		del.setText("删除");
		
		//添加toolItem的监听事件
		Listener listener = new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				if(event.widget == add) {
					TableItem tableItem = new TableItem(table, SWT.NONE);
					tableItem.setText(new String[] {"姓名100","年龄100","电话100"});
				}
				
			}
		};
		
		ToolItem up_move = new ToolItem(toolBar, SWT.NONE);
		up_move.setText("上移");
		add.addListener(SWT.Selection, listener);
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		composite.setLayout(gridLayout);
		
		table = new Table(composite, SWT.MULTI | SWT.FULL_SELECTION | SWT.CHECK);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		// 表格布局
        GridData gridData = new org.eclipse.swt.layout.GridData();
        gridData.horizontalAlignment = SWT.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.verticalAlignment = SWT.FILL;
        table.setLayoutData(gridData);
        
        TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
        tblclmnNewColumn.setWidth(100);
        tblclmnNewColumn.setText("姓名");
        
        TableColumn tableColumn = new TableColumn(table, SWT.NONE);
        tableColumn.setWidth(100);
        tableColumn.setText("年龄");
        
        TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
        tableColumn_1.setWidth(100);
        tableColumn_1.setText("电话");
        
        Menu menu = new Menu(shell,SWT.POP_UP);
        table.setMenu(menu);
        org.eclipse.swt.widgets.MenuItem menuItem = new org.eclipse.swt.widgets.MenuItem(menu, SWT.PUSH);
        menuItem.setText("添加");
        
        TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
        tableColumn_2.setWidth(100);
        tableColumn_2.setText("\u64CD\u4F5C");
        
        TableItem[] items = table.getItems();
		
		
		
		
		for (int i = 0; i < 5; i++) {
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText(new String[] {"姓名"+i,"性别"+i,"电话"+i});
		}
		for (int i = 0; i < items.length; i++) {
			TableEditor tableEditor = new TableEditor(table);
			Text text = new Text(table, SWT.NONE);
			text.setText(items[i].getText());
			tableEditor.grabHorizontal = true;
			tableEditor.setEditor(text,items[i],0);
		}
		
		for (int i = 0; i < items.length; i++) {
			TableEditor tableEditor = new TableEditor(table);
			Button btn = new Button(table, SWT.NONE);
			btn.setText("编辑");
			btn.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseUp(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseDown(MouseEvent arg0) {
//					String text = items[i].getText(0);
//					String text2 = items[i].getText(1);
//					MessageBox mBox = new MessageBox(shell, SWT.NONE);
//					mBox.setText("clumn1:" + text + ","+ "clumn2:" + text2);
				}
				
				@Override
				public void mouseDoubleClick(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			tableEditor.grabHorizontal = true;
			tableEditor.setEditor(btn,items[i],3);
		}

	}
}
