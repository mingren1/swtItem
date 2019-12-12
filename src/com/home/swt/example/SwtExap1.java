package com.home.swt.example;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Scale;

public class SwtExap1 {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Table table;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtExap1 window = new SwtExap1();
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
		shell.setSize(677, 425);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.VERTICAL));
		
		Composite composite = new Composite(shell, SWT.V_SCROLL);
		
		Label label = new Label(composite, SWT.NONE);
		label.setBounds(10, 25, 51, 20);
		label.setText("\u7528\u6237\u540D\uFF1A");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(67, 22, 73, 26);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setBounds(10, 71, 45, 20);
		label_1.setText("\u5BC6\u7801\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(67, 68, 73, 26);
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(25, 117, 98, 30);
		btnNewButton.setText("\u767B\u5F55");
		btnNewButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				MessageBox mBox = new MessageBox(shell);
				mBox.setMessage("我是message");
				mBox.setText("我是text");
				mBox.open();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(199, 25, 92, 28);
		combo.setText("\u6CB3\u5317");
		for (int i = 0;  i<10;  i++) {
			combo.add("第" + i + "项");
		}
		
		Button btnCheckButton = new Button(composite, SWT.CHECK);
		btnCheckButton.setBounds(333, 25, 121, 20);
		btnCheckButton.setText("Check Button");
		
		Button btnRadioButton = new Button(composite, SWT.RADIO);
		btnRadioButton.setBounds(490, 25, 119, 20);
		btnRadioButton.setText("Radio Button");
		
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		spinner.setBounds(199, 68, 57, 26);
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setBounds(199, 117, 110, 28);
		
		Link link = new Link(composite, SWT.NONE);
		link.setBounds(190, 159, 66, 20);
		link.setText("<a>\u94FE\u63A5</a>");
		
		List list = new List(composite, SWT.V_SCROLL);
		list.setBounds(333, 67, 57, 68);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setBounds(419, 84, 164, 95);
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		Composite composite_2 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_2);
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 76, 20);
		lblNewLabel.setText("New Label");
		
		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setBounds(92, 10, 73, 26);
		
		for (int i = 0;  i<10;  i++) {
			list.add("第" + i + "项");
		}
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("\u540D\u79F0");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("\u578B\u53F7");
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u5C3A\u5BF8");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u55B7\u5C04\u8DDD\u79BB");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {"1","2","3","4"});
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(100);
		tblclmnNewColumn_2.setText("\u64CD\u4F5C");
		tableItem.setText(4, "编辑");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(100);
		tblclmnNewColumn_3.setText("New Column");

	}
}
