package com.home.swt.tree.tr1;

import java.io.File;
import java.util.Vector;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
public class TreeTest {
 
	//递归方法
	public static void listFiles(File rootFile, TreeItem item) {
		File[] files = rootFile.listFiles();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				System.out.println(files[i].getName());
				TreeItem subItem = new TreeItem(item, SWT.None);
				subItem.setText(files[i].getName());
				listFiles(files[i], subItem);
			}
		}
	}
	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
 
		Vector<Category> categories = new Vector<Category>();
		Category category = new Category("Java libraries", null);
		categories.add(category);
		category = new Category("UI Toolkits", category);
		new Category("AWT", category);
		new Category("Swing", category);
		new Category("SWT/JFace", category);
		category = new Category("Java IDEs", null);
		categories.add(category);
		new Category("Eclipse", category);
		new Category("JBuilder", category);
		shell.setSize(300, 200);
		{
			Composite composite = new Composite(shell, SWT.NONE);
			composite.setLayout(new FillLayout(SWT.HORIZONTAL));
			{
				final Tree tree = new Tree(composite, SWT.BORDER
						| SWT.FULL_SELECTION);
				tree.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						File file = new File("D:\\QQ");
						File[] files = file.listFiles();
						tree.setItemCount(0);
						for (int i = 0; i < files.length; i++) {
							TreeItem item = new TreeItem(tree, SWT.NONE);
							item.setText(files[i].getName());
							//调用递归方法
							listFiles(files[i], item);
						}
					}
				});
				tree.setLinesVisible(true);
				tree.setHeaderVisible(true);
				{
					TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
					treeColumn.setWidth(100);
					treeColumn.setText("New Column");
				}
			}
		}
		shell.open();
 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
 
	static class Category {
 
		private String name;
		private Vector<Category> subCategories;
		private Category parent;
 
		public Category(String name, Category parent) {
			this.name = name;
			this.parent = parent;
			if (parent != null)
				parent.addSubCategory(this);
		}
 
		public Vector<Category> getSubCategories() {
			return subCategories;
		}
 
		private void addSubCategory(Category subcategory) {
			if (subCategories == null)
				subCategories = new Vector<Category>();
			if (!subCategories.contains(subcategory))
				subCategories.add(subcategory);
		}
 
		public String getName() {
			return name;
		}
 
		public Category getParent() {
			return parent;
		}
	}
}
