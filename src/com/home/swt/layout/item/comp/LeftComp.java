package com.home.swt.layout.item.comp;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class LeftComp {

	private Tree tree;
	private TreeEditor treeEditor;
	private static Map<String, Image> mapping = new HashMap<String, Image>();
	private Display display;

	public LeftComp(Shell parent,int style) {
		Image image = getimage("images/smilFace.png");
		parent.setImage(image);
		Composite leftComposite = new Composite(parent, SWT.NONE);
		this.display = parent.getDisplay();
		
		leftComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gridData0 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		leftComposite.setLayoutData(gridData0);
		Tree tree = new Tree(leftComposite, SWT.BORDER);
		this.tree = tree;
		Display display = parent.getDisplay();
		// 渲染树数据
		renderTreeData(display);
		// 新增树节点
		addTreeMenuListen();
		//设置树节点 可移动
		initDnD(display, tree);
		//设置树节点可编辑
		setTreeEditor();
		//树节点 重命名
		addTreeRenameListen();
	}

	private void addTreeRenameListen() {
		tree.addMouseListener(
				new MouseAdapter() {
					@Override
//					public void mouseDoubleClick(MouseEvent e) {
//						Point p = new Point(e.x, e.y);
//						// 通过点击位置的y坐标和第一列的任意x坐标获取当前行项目，其他列对应的item为null
//						// final TreeItem item = getCurrentItem(tree, e.y);
//						TreeItem item = tree.getItem(p);
//						if (item == null)
//							return;
//						Control old = treeEditor.getEditor();
//						if (old != null) {
//							old.dispose();
//						}
//						final int currentCol = getColId(tree, item, p);
//						final Text text = new Text(tree, SWT.RIGHT);
//						final Combo combo = new Combo(tree, SWT.READ_ONLY);
//						switch (currentCol) {
//						case 0:
//						case 1:
//						case 2:
//							text.setForeground(item.getForeground());
//							text.setText(item.getText(currentCol));
//							text.selectAll();
//							text.setFocus();
//							text.addModifyListener(new ModifyListener() {
//								@Override
//								public void modifyText(ModifyEvent e) {
//									item.setText(currentCol, text.getText());
//								}
//							});
//							// treeEditor.minimumWidth = item.getBounds(currentCol).width;
//							treeEditor.setEditor(text, item, currentCol);
//							break;
//						case 3:
//							combo.setForeground(item.getForeground());
//							combo.add("借");
//							combo.add("贷");
//							combo.setText(item.getText(currentCol));
//							combo.setFocus();
//							combo.addSelectionListener(new SelectionAdapter() {
//								public void widgetSelected(SelectionEvent e) {
//									item.setText(currentCol, combo.getText());
//								}
//							});
//							// treeEditor.minimumWidth = item.getBounds(currentCol).width;
//							treeEditor.setEditor(combo, item, currentCol);
//							break;
//			
//						}
//						setChildrenCheck(item);
//			
//					}
					public void mouseDoubleClick(MouseEvent e) {
						Point p = new Point(e.x, e.y);
						TreeItem item = tree.getItem(p);
						if (item == null)
							return;
						Control old = treeEditor.getEditor();
						if (old != null) {
							old.dispose();
						}
						Text text = new Text(tree, SWT.RIGHT);
						text.setForeground(item.getForeground());
						text.setText(item.getText());
						text.selectAll();
						text.setFocus();
						text.addModifyListener(new ModifyListener() {
							@Override
							public void modifyText(ModifyEvent e) {
								item.setText(text.getText());
							}
						});
						treeEditor.setEditor(text, item);
						
//						final int currentCol = getColId(tree, item, p);
//						final Text text = new Text(tree, SWT.RIGHT);
//						final Combo combo = new Combo(tree, SWT.READ_ONLY);
//						switch (currentCol) {
//						case 0:
//						case 1:
//						case 2:
//							text.setForeground(item.getForeground());
//							text.setText(item.getText(currentCol));
//							text.selectAll();
//							text.setFocus();
//							text.addModifyListener(new ModifyListener() {
//								@Override
//								public void modifyText(ModifyEvent e) {
//									item.setText(currentCol, text.getText());
//								}
//							});
//							// treeEditor.minimumWidth = item.getBounds(currentCol).width;
//							treeEditor.setEditor(text, item, currentCol);
//							break;
//						case 3:
//							combo.setForeground(item.getForeground());
//							combo.add("借");
//							combo.add("贷");
//							combo.setText(item.getText(currentCol));
//							combo.setFocus();
//							combo.addSelectionListener(new SelectionAdapter() {
//								public void widgetSelected(SelectionEvent e) {
//									item.setText(currentCol, combo.getText());
//								}
//							});
//							// treeEditor.minimumWidth = item.getBounds(currentCol).width;
//							treeEditor.setEditor(combo, item, currentCol);
//							break;
//							
//						}
//						setChildrenCheck(item);
						
					}
					@Override
//					public void mouseDown(MouseEvent e) {
//						Control editor = treeEditor.getEditor();
//						if(null != editor) {
//							editor.dispose();
//						}
//					}
					public void mouseDown(MouseEvent e) {
						Point point = new Point(e.x, e.y);
						TreeItem item = tree.getItem(point);
//						item.
						Control editor = treeEditor.getEditor();
						if(null != editor) {
							editor.dispose();
						}
					}

		});

	}

	protected void setChildrenCheck(TreeItem item) {
		// TreeItem的删除
        // if (parent.getChecked()) {
        // parent.removeAll();
        // parent.dispose();
        // }
        TreeItem[] children = item.getItems();
        if (item.getChecked()) {
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

	protected int getColId(Tree tree2, TreeItem item, Point p) {
		int column = -1;
        for (int i = 0, n = tree.getColumnCount(); i < n; i++) {
            Rectangle rect = item.getBounds(i);// 得到各列的边界
            if (rect.contains(p)) {
                column = i;
                break;
            }
        }
        return column;
	}

//	private void setTreeEditor() {
//		treeEditor = new TreeEditor(tree);
//		treeEditor.horizontalAlignment = SWT.LEFT;
//		treeEditor.grabHorizontal = true;
//		treeEditor.minimumWidth = 30;
//		
//
//		// 为树创建5列
//		for (int i = 0; i < 1; i++) {
//			TreeColumn column = new TreeColumn(tree, SWT.NONE);
//			column.setText("column" + i);
//		}
//		for (int i = 0; i < tree.getColumnCount(); i++)
//			tree.getColumn(i).pack();
//		// 设置网格线可见
//		tree.setLinesVisible(false);
//		// 设置表头可见
//		tree.setHeaderVisible(false);
//
//	}
	
	
	private void setTreeEditor() {
		
		treeEditor = new TreeEditor(tree);
		treeEditor.horizontalAlignment = SWT.LEFT;
		treeEditor.grabHorizontal = true;
		treeEditor.minimumWidth = 30;
		//设置编辑项
		TreeItem[] items = tree.getItems();
		if(null != items) {
			for (TreeItem treeItem : items) {
				setChildTreeItemEditor(treeItem);
			}
		}
		
		
	}

	private void setChildTreeItemEditor(TreeItem treeItem) {
		String text = treeItem.getText();
		//创建要绑定的对象
		Text editor = new Text(treeItem.getParent(), SWT.NONE);
		editor.setText(text);
		Control editor2 = treeEditor.getEditor();
		if(null != editor2) {
			editor2.dispose();
		}
		treeEditor.setEditor(editor, treeItem);
	}

	public Tree getTree() {
		return tree;
	}

	private void renderTreeData(Display display) {

		Image[] images = new Image[] { display.getSystemImage(SWT.ICON_WARNING), display.getSystemImage(SWT.ICON_ERROR),
				display.getSystemImage(SWT.ICON_INFORMATION), display.getSystemImage(SWT.ICON_QUESTION) };

		Image image = getimage("images/wjj.png");
		// 初始化树数据
		for (int i = 0; i < 4; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText("子功能节点 " + i);
			// item.setImage(images[i % images.length]);
			 item.setImage(image);
			// mapping.put("子功能节点 " + i, images[i % images.length]);
			 mapping.put("子功能节点 " + i, image);
		}

	}

	private Image getimage(String path) {
		Image image1 = new Image(display, path);
		Rectangle bounds = image1.getBounds();
		Image image = new Image(display, 20, 20);
		GC gc = new GC(image); 
		gc.drawImage(image1, 0, 0, bounds.width, bounds.height, 0, 0, 20, 20);
		gc.dispose();
		return image;
	}

	// 树节点移动
	private void initDnD(Display display, Tree tree2) {
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK;

		final DragSource source = new DragSource(tree, operations);
		source.setTransfer(types);

		final TreeItem[] dragSourceItem = new TreeItem[1];
		source.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				TreeItem[] selection = tree.getSelection();
				if (selection.length > 0 && selection[0].getItemCount() == 0) {
					event.doit = true;
					dragSourceItem[0] = selection[0];
				} else {
					event.doit = false;
				}
			};

			public void dragSetData(DragSourceEvent event) {
				event.data = dragSourceItem[0].getText();
			}

			public void dragFinished(DragSourceEvent event) {
				if (event.detail == DND.DROP_MOVE)
					dragSourceItem[0].dispose();
				dragSourceItem[0] = null;
			}
		});

		DropTarget target = new DropTarget(tree, operations);
		target.setTransfer(types);
		target.addDropListener(new DropTargetAdapter() {
			public void dragOver(DropTargetEvent event) {
				event.feedback = DND.FEEDBACK_SCROLL;
				if (event.item != null) {
					TreeItem item = (TreeItem) event.item;
					Point pt = display.map(null, tree, event.x, event.y);
					Rectangle bounds = item.getBounds();
					if (pt.y < bounds.y + bounds.height / 3) {
						event.feedback |= DND.FEEDBACK_INSERT_BEFORE;
					} else if (pt.y > bounds.y + 2 * bounds.height / 3) {
						event.feedback |= DND.FEEDBACK_INSERT_AFTER;
					}
				}
			}

			public void drop(DropTargetEvent event) {
				if (event.data == null) {
					event.detail = DND.DROP_NONE;
					return;
				}
				String text = (String) event.data;
				if (event.item != null) {
					TreeItem item = (TreeItem) event.item;
					Point pt = display.map(null, tree, event.x, event.y);
					Rectangle bounds = item.getBounds();

					TreeItem[] items = tree.getItems();
					int index = 0;
					for (int i = 0; i < items.length; i++) {
						if (items[i] == item) {
							index = i;
							break;
						}
					}

					if (pt.y < bounds.y + bounds.height / 3) {
						TreeItem newItem = new TreeItem(tree, SWT.NONE, index);
						newItem.setText(text);
						newItem.setImage(mapping.get(text));
					} else if (pt.y > bounds.y + 2 * bounds.height / 3) {
						TreeItem newItem = new TreeItem(tree, SWT.NONE, index + 1);
						newItem.setText(text);
						newItem.setImage(mapping.get(text));
					} else {
						TreeItem newItem = new TreeItem(item, SWT.NONE);
						newItem.setText(text);
						newItem.setImage(mapping.get(text));
					}
				}
			}
		});

	}

	public void addTreeMenuListen() {
		tree.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent arg0) {

			}

			@Override
			public void mouseDown(MouseEvent e) {

				Menu menu = new Menu(tree);
				MenuItem addNode = new MenuItem(menu, SWT.PUSH);
				Image image = getimage("images/add.png");
				addNode.setImage(image);
				addNode.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent arg0) {

						if (addNode == arg0.widget) {
							TreeItem item = tree.getItem(new Point(e.x, e.y));
							Image image = getimage("images/wjj.png");
							if (null == item) {
								TreeItem ti = new TreeItem(tree, SWT.NONE);
								ti.setImage(image);
								ti.setText("Item ");
								setChildTreeItemEditor(ti);
							} else {
								TreeItem ti = new TreeItem(item, SWT.LEFT);
								ti.setImage(image);
								ti.setText("Item ");
								setChildTreeItemEditor(ti);
							}
						}
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {

					}
				});
				addNode.setText("添加节点");
				tree.setMenu(menu);

			}

			// private TreeEditor treeEditor = new TreeEditor(tree);
			@Override
			public void mouseDoubleClick(MouseEvent e) {

			}

			// 获取当前列号的方法
			private int getColId(Tree tree, TreeItem item, Point clickPoint) {
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

		});

	}

}
