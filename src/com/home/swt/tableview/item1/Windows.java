package com.home.swt.tableview.item1;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Windows {

	protected Shell shell;
	private Table table;
	// private Tree table;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Windows window = new Windows();
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
		// shell.setImage(SWTResourceManager.getImage(Windows.class, "/image/IOC
		// (302).ico"));
		shell.setSize(577, 407);
		shell.setText("��һ�� �Ӵ�SWT");
		// ��ʼ��
		TableViewer tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		table = tableViewer.getTable();
		// CheckboxTreeViewer tableViewer=new CheckboxTreeViewer(shell,SWT.BORDER |
		// SWT.FULL_SELECTION);
		// table = tableViewer.getTree();
		table.setToolTipText("");
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		TableLayout tlayout = new TableLayout();
		table.setLayout(tlayout);
		// ʹ��tree ʹ��
		// TreeColumn tableColumn_1 = new TreeColumn(table, SWT.NONE);
		// tableColumn_1.setWidth(177);
		// tableColumn_1.setText("�û�id");
		// ���ÿ��Ϊ11
		tlayout.addColumnData(new ColumnWeightData(25));
		// ���ñ���е�����
		new TableColumn(table, SWT.NONE).setText("�û�id");
		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("����");
		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("�Ա�");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("����");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("��ַ");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("����");
		table.setBounds(29, 10, 540, 328);
		Button button = new Button(shell, SWT.NONE);
//		button.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// ����һ��checkbox����
//				CheckboxTableViewer check = new CheckboxTableViewer(table);
//				// ��ȡ���е�checkbox
//				Object obj[] = check.getCheckedElements();
//				ArrayList<UserInfoList> list = new ArrayList<UserInfoList>();
//				// ����ȡ����checkbox �ŵ�list�� ���ѡ��һ��������Ϣ
//				for (Object b : obj) {
//					// �жϻ�ȡ���Ƿ���userinfolist����
//					if (b instanceof UserInfoList) {
//						list.add((UserInfoList) b);
//					}
//					if (list != null && list.size() > 0 && list.size() == 1) {
//						Shows show = new Shows(list.get(0));
//						show.open();
//					}
//				}
//			}
//		});
		button.setBounds(390, 344, 72, 22);
		button.setText("ѡ��һ��");
		/**
		 * ��People�ļ�¼�������tableViewer�У�ע��setInput�Ĳ�����ObjectҲ����˵�����Խ����κβ�����
		 * ����������ܵĻ���Java��Collection(����,����ΪList)�������飬
		 * ��ôtv����ô֪���������ʾ��ô�����ʽǧ���������ݵ���?���������������ͱ�ǩ��(����������һ���ڲ���)
		 */
		// ����������
		tableViewer.setContentProvider(new ContextProvider());
		// ���ñ�ǩ��
		tableViewer.setLabelProvider(new tableLableProvider());
		// �����ݼ��ϸ�tableView
		tableViewer.setInput(new Data().addlist());
		// ���������������ͱ�ǩ�����ܴ�setInput�õ������ݼ��Ϸֽ����ʾ�����Ҫ�����ݡ�����һ�����͵�mvc��ʵ��.
	}
}









	// �ϴ�����
//public void widgetSelected(SelectionEvent e) {
////�ϴ�
//FileDialog dialog=new FileDialog(shell);
//dialog.setText("open");
//dialog.setFilterNames(new String[]{"ͼƬ�ļ�(*.jpg)","ͼƬ�ļ���*.gif��"});
//dialog.setFilterExtensions(new String[]{"*.jpg", "*.gif"});
//String fileName = dialog.open();//ȡ�ô�ͼƬ�������ַ
//if (fileName!=null){
//text.setText(fileName);
//// Canvas.redraw();
//}
