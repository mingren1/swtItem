package com.home.swt.tree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class MainApp {

	protected Shell shell;
	static LeftCom left;
	static RightCom right;
	
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
		shell.setText("主窗口");
		//布局
		FillLayout fillLayout = new FillLayout(SWT.HORIZONTAL);
		this.shell.setLayout(fillLayout);
		
		//左右分栏  左边功能树 右边功能面板
		final SashForm sashForm = new SashForm(shell, SWT.NONE);
//		sashForm.SASH_WIDTH = 2;
		left = new LeftCom(sashForm,SWT.NONE);
		Tree tree = left.getTree();
		
		TreeEditor treeEditor = new TreeEditor(tree);
		treeEditor.horizontalAlignment = SWT.LEFT;
		treeEditor.grabHorizontal = true;
		treeEditor.minimumWidth = 30;
		
		tree.addMouseListener(new MouseAdapter() {
		      
		      @Override
		      public void mouseDown(MouseEvent e) {
		          Point p = new Point(e.x, e.y);
		          // 通过点击位置的y坐标和第一列的任意x坐标获取当前行项目，其他列对应的item为null
//		          final TreeItem item = getCurrentItem(tree, e.y);
		          TreeItem item = tree.getItem(p);
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
		                  combo.add("借");
		                  combo.add("贷");
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
		
		// 为树创建5列
		for (int i = 0; i < 5; i++) {
			TreeColumn column = new TreeColumn(tree, SWT.NONE);
//			column.setResizable(true);
			column.setText("column" + i);
		}
		for (int i = 0; i < tree.getColumnCount(); i++)
			tree.getColumn(i).pack();
		// 设置网格线可见
		tree.setLinesVisible(true);
		// 设置表头可见
		tree.setHeaderVisible(true);
		
		right = new RightCom(sashForm,SWT.NONE);
		
//		sashForm.setWeights(new int[] {30,70});
		
		

	}
	
	
	protected TreeItem getCurrentItem(Tree tree, int y) {
		TreeItem item = null;
		int firstColWidth = tree.getColumn(0).getWidth();
		for (int i = 0; i < firstColWidth; i++) {
			item = tree.getItem(new Point(i, y));
			if (item != null)
				return item;
		}
		return item;
	}

	protected void setChildrenCheck(TreeItem parent) {
		// TODO Auto-generated method stub
		// TreeItem的删除
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

	protected int getColId(Tree tree, TreeItem item, Point clickPoint) {
		int column = -1;
        for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
            Rectangle rect = item.getBounds(i);// 得到各列的边界
            if (rect.contains(clickPoint)) {
                column = i;
                break;
            }
        }
        return column;
	}

	public static void main(String[] args) {
		try {
			MainApp window = new MainApp();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
