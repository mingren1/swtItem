package com.home.swt.tree.tr1;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.core.internal.runtime.Activator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.TreeItem;

public class TreeEditor1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TreeEditor1 window = new TreeEditor1();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FormLayout());
		
		Composite composite = new Composite(shell, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 215);
		fd_composite.right = new FormAttachment(0, 166);
		fd_composite.top = new FormAttachment(0, 5);
		fd_composite.left = new FormAttachment(0, 5);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite, SWT.BORDER);
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("New TreeItem1");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("New TreeItem2");
		trtmNewTreeitem_1.setExpanded(true);
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_2.setText("New TreeItem3");
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.bottom = new FormAttachment(0, 215);
		fd_composite_1.right = new FormAttachment(0, 372);
		fd_composite_1.top = new FormAttachment(0, 5);
		fd_composite_1.left = new FormAttachment(0, 184);
		composite_1.setLayoutData(fd_composite_1);
		
		tree.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
//				Control oldEditor = editor.getEditor();
				TreeEditor oldEditor = new TreeEditor(tree);
//				if(oldEditor != null) {
//					oldEditor.dispose();
//				}
				TreeItem item = (TreeItem)e.item;
				if(item == null) {
					return;
				}
				Text newEditor = new Text(tree, SWT.BORDER);
				newEditor.setText(item.getText());
//				newEditor.addModifyListener(new ModifyListener() {
//					@Override
//					public void modifyText(ModifyEvent e) {
//						Text text = (Text)editor.getEditor();
//						editor.getItem().setText(text.getText());
//					}
//				});
//				newEditor.selectAll();
//				newEditor.setFocus();
				oldEditor.setEditor(newEditor, item);
			}
			
		});

	}
}
