package com.home.swt.tree.tr1;

import org.eclipse.swt.widgets.Combo;

import org.eclipse.swt.widgets.Control;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.layout.FillLayout;

import org.eclipse.swt.SWT;

import org.eclipse.swt.custom.TreeEditor;

import org.eclipse.swt.widgets.Tree;

import org.eclipse.swt.widgets.TreeColumn;

import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.swt.events.ModifyEvent;

import org.eclipse.swt.events.ModifyListener;

import org.eclipse.swt.events.MouseAdapter;

import org.eclipse.swt.events.MouseEvent;

import org.eclipse.swt.events.SelectionAdapter;

import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.graphics.Point;

import org.eclipse.swt.graphics.Rectangle;

/**
 * ������̳�
 * 
 * @author 42194
 *
 */
public class Window {

	private Shell shell;

	private TreeColumn treeColumn_1;

	/**
	 * 
	 * Launch the application.
	 * 
	 * 
	 * 
	 * @param args
	 * 
	 */

	public static void main(String[] args) {

		try {

			Window window = new Window();

			window.open();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * Open the window.
	 * 
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
	 * 
	 * Create contents of the window.
	 * 
	 */

	protected void createContents() {

		shell = new Shell(SWT.CLOSE);

		shell.setSize(690, 481);

		shell.setText("���������");

		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		windowToCenter(shell);

		final Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);

		tree.setLinesVisible(true);

		tree.setHeaderVisible(true);

		TreeColumn treeColumn = new TreeColumn(tree, SWT.CENTER);

		treeColumn.setResizable(false);

		treeColumn.setWidth(200);

		treeColumn.setText("��Ŀ����");

		treeColumn_1 = new TreeColumn(tree, SWT.LEFT);

		treeColumn_1.setImage(null);

		treeColumn_1.setWidth(100);

		treeColumn_1.setText("��Ŀ����");

		treeColumn = new TreeColumn(tree, SWT.LEFT);

		treeColumn.setWidth(100);

		treeColumn.setText("��Ŀ����");

		treeColumn = new TreeColumn(tree, SWT.CENTER);

		treeColumn.setWidth(100);

		treeColumn.setText("�������");

		TreeItem root1 = new TreeItem(tree, SWT.NONE);

		root1.setText(new String[] { "1000", "�����ʽ�", "1", "��" });

		TreeItem root2 = new TreeItem(tree, SWT.NONE);

		root2.setText(new String[] { "2000", "���", "1", "��" });

		TreeItem root3 = new TreeItem(root2, SWT.NONE);

		root3.setText(new String[] { "2100", "�����Ʒ", "2", "��" });

		TreeItem root4 = new TreeItem(root3, SWT.NONE);

		root4.setText(new String[] { "2110", "��ƷA", "3", "��" });

		TreeItem root5 = new TreeItem(root4, SWT.NONE);

		root5.setText(new String[] { "2111", "�׳�����", "4", "��" });

		TreeItem root6 = new TreeItem(root4, SWT.NONE);

		root6.setText(new String[] { "2112", "�ҳ�����", "4", "��" });

		// �ڵ�״̬�ж�

		// if(treeItem1.getExpanded()){do something}

		tree.addMouseListener(new MouseAdapter() {

			private TreeEditor treeEditor = new TreeEditor(tree);

			@Override

			public void mouseDown(MouseEvent e) {

				Point p = new Point(e.x, e.y);

				// ͨ�����λ�õ�y����͵�һ�е�����x�����ȡ��ǰ����Ŀ�������ж�Ӧ��itemΪnull

				final TreeItem item = getCurrentItem(tree, e.y);

				if (item == null)

					return;

				Control old = treeEditor.getEditor();

				if (old != null) {

					old.dispose();

				}

				final int currentCol = getColId(tree, item, p);

				final Text text = new Text(tree, SWT.RIGHT);

				final Combo combo = new Combo(tree, SWT.READ_ONLY);

				switch (currentCol) {

				case 0:

				case 1:

				case 2:

					text.setForeground(item.getForeground());

					text.setText(item.getText(currentCol));

					text.selectAll();

					text.setFocus();

					text.addModifyListener(new ModifyListener() {

						@Override

						public void modifyText(ModifyEvent e) {

							item.setText(currentCol, text.getText());

						}

					});

					treeEditor.minimumWidth = item.getBounds(currentCol).width;

					treeEditor.setEditor(text, item, currentCol);

					break;

				case 3:

					combo.setForeground(item.getForeground());

					combo.add("��");

					combo.add("��");

					combo.setText(item.getText(currentCol));

					combo.setFocus();

					combo.addSelectionListener(new SelectionAdapter() {

						public void widgetSelected(SelectionEvent e) {

							item.setText(currentCol, combo.getText());

						}

					});

					treeEditor.minimumWidth = item.getBounds(currentCol).width;

					treeEditor.setEditor(combo, item, currentCol);

					break;

				}

				setChildrenCheck(item);

			}

		});

	}

	// ��ȡ��ǰ�кŵķ���

	private int getColId(Tree tree, TreeItem item, Point clickPoint) {

		int column = -1;

		for (int i = 0, n = tree.getColumnCount(); i < n; i++) {

			Rectangle rect = item.getBounds(i);// �õ����еı߽�

			if (rect.contains(clickPoint)) {

				column = i;

				break;

			}

		}

		return column;

	}

	/**
	 * 
	 * ���ݵ��λ�û�ȡ��ǰtreeItem
	 * 
	 * 
	 * 
	 * @param tree
	 * 
	 * @param clickY
	 * 
	 *            ���λ�õ�y����
	 * 
	 * @return
	 * 
	 */

	private TreeItem getCurrentItem(Tree tree, int clickY) {

		TreeItem item = null;

		int firstColWidth = tree.getColumn(0).getWidth();

		for (int i = 0; i < firstColWidth; i++) {

			item = tree.getItem(new Point(i, clickY));

			if (item != null)

				return item;

		}

		return item;

	}

	/**
	 * 
	 * ����Ŀ��ѡ����游��Ŀ��ѡ��״̬
	 * 
	 * 
	 * 
	 * @param parent
	 * 
	 */

	private void setChildrenCheck(TreeItem parent) {

		// TreeItem��ɾ��

		// if (parent.getChecked()) {

		// parent.removeAll();

		// parent.dispose();

		// }

		TreeItem[] children = parent.getItems();

		if (parent.getChecked()) {

			for (int i = 0; i < children.length; i++) {

				children[i].setChecked(true);

				setChildrenCheck(children[i]);

			}

		} else {

			for (int i = 0; i < children.length; i++) {

				children[i].setChecked(false);

				setChildrenCheck(children[i]);

			}

		}

	}

	/**
	 * 
	 * ���ھ���
	 * 
	 * @param shell
	 * 
	 */

	private void windowToCenter(Shell shell) {

		int screenWidth = Display.getCurrent().getClientArea().width;

		int screenHeight = Display.getCurrent().getClientArea().height;

		int windowWidth = shell.getShell().getSize().x;

		int windowHeight = shell.getShell().getSize().y;

		shell.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);

	}

}
