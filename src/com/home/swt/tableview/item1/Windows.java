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
		shell.setText("第一次 接触SWT");
		// 初始化
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
		// 使用tree 使用
		// TreeColumn tableColumn_1 = new TreeColumn(table, SWT.NONE);
		// tableColumn_1.setWidth(177);
		// tableColumn_1.setText("用户id");
		// 设置宽度为11
		tlayout.addColumnData(new ColumnWeightData(25));
		// 设置表格列的名称
		new TableColumn(table, SWT.NONE).setText("用户id");
		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("姓名");
		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("性别");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("年龄");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("地址");

		tlayout.addColumnData(new ColumnWeightData(30));
		new TableColumn(table, SWT.NONE).setText("邮箱");
		table.setBounds(29, 10, 540, 328);
		Button button = new Button(shell, SWT.NONE);
//		button.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				// 创建一个checkbox对象
//				CheckboxTableViewer check = new CheckboxTableViewer(table);
//				// 获取所有的checkbox
//				Object obj[] = check.getCheckedElements();
//				ArrayList<UserInfoList> list = new ArrayList<UserInfoList>();
//				// 将获取到的checkbox 放到list中 如果选择一个弹出信息
//				for (Object b : obj) {
//					// 判断获取的是否是userinfolist对象
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
		button.setText("选择一个");
		/**
		 * 当People的记录集输入的tableViewer中，注意setInput的参数是Object也就是说它可以接受任何参数，
		 * 不过它最常接受的还是Java的Collection(集合,这里为List)或者数组，
		 * 那么tv是怎么知道如何来显示这么输入格式千差万别的数据的呢?就是依靠内容器和标签器(这里它两是一个内部类)
		 */
		// 设置内容器
		tableViewer.setContentProvider(new ContextProvider());
		// 设置标签器
		tableViewer.setLabelProvider(new tableLableProvider());
		// 把数据集合给tableView
		tableViewer.setInput(new Data().addlist());
		// 这样利用内容器和标签器就能从setInput得到的数据集合分解出显示表格需要的数据。这是一个典型的mvc的实现.
	}
}









	// 上传数据
//public void widgetSelected(SelectionEvent e) {
////上传
//FileDialog dialog=new FileDialog(shell);
//dialog.setText("open");
//dialog.setFilterNames(new String[]{"图片文件(*.jpg)","图片文件（*.gif）"});
//dialog.setFilterExtensions(new String[]{"*.jpg", "*.gif"});
//String fileName = dialog.open();//取得打开图片的物理地址
//if (fileName!=null){
//text.setText(fileName);
//// Canvas.redraw();
//}
