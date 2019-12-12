package com.home.swt.layout;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
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
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class SwtLayoutExap33 {

	protected Shell shell;
	private static Map<String, Image> mapping = new HashMap<String, Image>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtLayoutExap33 window = new SwtLayoutExap33();
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
		createContents(display);
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
	protected void createContents(Display display) {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT 主窗口");
		shell.setLayout(new GridLayout(5,false));
		//设置快捷选项卡
		Composite shortcutTabComp = new Composite(shell, SWT.NONE);
		shortcutTabComp.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData shortcutGridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 1);
		shortcutTabComp.setLayoutData(shortcutGridData);
		ToolBar mainToolBar = new ToolBar(shortcutTabComp, SWT.FLAT);
		ToolItem toolItem1 = new ToolItem(mainToolBar, SWT.PUSH);
//				toolItem1.setText("工具1");
		ImageData imageData = new ImageData("C:/Users/42194/Desktop/Capture001.png");
		imageData.width = 15;
		imageData.height = 15;
		final Image image = new Image(mainToolBar.getDisplay(), imageData);
		toolItem1.setImage(image);
		ToolItem toolItem2 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem2.setText("工具2");
		ToolItem toolItem3 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem3.setText("工具3");
		
		Composite composite_0 = new Composite(shell, SWT.NONE);
		composite_0.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gridData0 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		composite_0.setLayoutData(gridData0);
		Tree tree = new Tree(composite_0, SWT.BORDER);
		//渲染树节点数据
		renderTreeData(tree,display);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(1,false));
		GridData gridData1 = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		gridData1.widthHint = 500;
		composite_1.setLayoutData(gridData1);
		
		//增加树的子节点的监听事件
		setTreeListener(tree,composite_1);
		

	}

	private void setTreeListener(Tree tree,Composite composite) {
		//添加选择监听事件
		tree.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti = (TreeItem)arg0.item;
				//清除显示构件中的所有的组件
				Control[] children = composite.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				if(ti.getText().equalsIgnoreCase("子功能节点 1")) {
					//创建 表的控件
					createTableControllers(composite);
				}
				if(ti.getText().equalsIgnoreCase("子功能节点 2")) {
					//创建 选项卡的控件组合
					createTabControllers(composite);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//添加移动监听事件
		
	}

	protected void createTabControllers(Composite composite) {
		final Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false));
		label.setText("功能A");
		
		//设置选项卡组件
		final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("子功能1");
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("子功能2");
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("子功能3");
		
		composite.layout();
		
	}

	protected void createTableControllers(Composite composite) {
		createToolBarAndTable(composite);
	}

	// 创建工具栏
    private void createToolBarAndTable(Composite composite) {
    	ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
    	toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        final ToolItem add = new ToolItem(toolBar, SWT.PUSH);
        add.setText("添加");
//        add.setImage(new Image(toolBar.getDisplay(), "icons//add.gif"));
        final ToolItem del = new ToolItem(toolBar, SWT.PUSH);
        del.setText("删除");
//        del.setImage(new Image(toolBar.getDisplay(), "icons//delete.gif"));
        final ToolItem back = new ToolItem(toolBar, SWT.PUSH);
        back.setText("上移");
//        back.setImage(new Image(toolBar.getDisplay(), "icons//up.gif"));
        final ToolItem forward = new ToolItem(toolBar, SWT.PUSH);
        forward.setText("下移");
//        forward.setImage(new Image(toolBar.getDisplay(), "icons//down.gif"));
        final ToolItem save = new ToolItem(toolBar, SWT.PUSH);
        save.setText("保存");
//        save.setImage(new Image(toolBar.getDisplay(), "icons//save.gif"));
        Table table = new Table(composite, SWT.BORDER);
		table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn tableColumn1 = new TableColumn(table,SWT.NONE);
		tableColumn1.setWidth(100);
		tableColumn1.setText("姓名");
		TableColumn tableColumn2 = new TableColumn(table,SWT.NONE);
		tableColumn2.setWidth(100);
		tableColumn2.setText("性别");
		TableColumn tableColumn3 = new TableColumn(table,SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("年龄");
		
		TableItem item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"郑六1", "女1", "5681"});
     // 工具栏按钮事件处理
        Listener listener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                // 如果单击添加按钮，添加一行，在实际的项目实现中通常是接收输入的参数，案后添加
                // 这里为了简单起见，添加固定的一条记录
                if (event.widget == add)
                {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{"郑六", "女", "568"});
                }
                // 如果单击删除按钮
                else if (event.widget == del)
                {
                    // 首先获得表格中所有的行
                    TableItem[] items = table.getItems();
                    // 循环所有行
                    for (int i = items.length - 1; i >= 0; i--)
                    {
                        // 如果该行没有被选中，继续循环
                        if (!items[i].getChecked())
                            continue;
                        // 否则选中，查找该表格中是否有该行
                        int index = table.indexOf(items[i]);
                        // 如果没有该行，继续循环
                        if (index < 0)
                            continue;
                        table.remove(index);
                        System.out.println("i=" + i + ", index=" + index);
                        System.out.println("行数:" + table.getItemCount());
                        // table.pack();
                    }
                }
                // 如果单击上移按钮
                else if (event.widget == back)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > 0)
                        table.setSelection(selectedRow - 1);// 设置选中的行数
                }
                // 如果单击下移按钮
                else if (event.widget == forward)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > -1
                            && selectedRow < table.getItemCount() - 1)
                        table.setSelection(selectedRow + 1);// 设置选中的行数
                }
                // 如果单击保存按钮
                else if (event.widget == save)
                {
                    TableItem[] items = table.getItems();
                    // 保存到文件或数据库中，数据持久化，这里省略
                    for (int i = 0; i < items.length; i++)
                        for (int j = 0; j < table.getColumnCount(); j++)
                            System.out.println(items[i].getText(j));
                }
            }

        };
        // 为工具栏的按钮注册事件
        add.addListener(SWT.Selection, listener);
        del.addListener(SWT.Selection, listener);
        back.addListener(SWT.Selection, listener);
        forward.addListener(SWT.Selection, listener);
        save.addListener(SWT.Selection, listener);
        
        TableItem[] items = table.getItems();
        for (int i = 0; i < items.length; i++) {
			TableEditor tableEditor = new TableEditor(table);
//			tableEditor.horizontalAlignment = SWT.RIGHT;
			Text text = new Text(table, SWT.NONE);
			text.setText(items[i].getText(0));
			tableEditor.grabHorizontal = true;
			//组件绑定
			tableEditor.setEditor(text,items[i],0);
			//增加text的内容修改监听事件
			text.addModifyListener(new ModifyListener() {
	          public void modifyText(ModifyEvent e) {
	        	  tableEditor.getItem().setText(0, text.getText());
	          }
	        });
			
		}
        
        createMenu(table);
        composite.layout();

    }
    
 // 创建上下文菜单
    private void createMenu(Table table){
        // 创建弹出式菜单
    	Menu menu = new Menu(shell, SWT.POP_UP);
        // 设置该菜单为表格菜单
//        table.setMenu(menu);
        // 创建删除菜单项
        MenuItem del = new MenuItem(menu, SWT.PUSH);
        del.setText("删除");
//        del.setImage(new Image(toolBar.getDisplay(), "icons//delete.gif"));
        // 为删除菜单注册事件，当单击时，删除所选择的行
        del.addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event event)
            {
                // 此处需添加删除绑定Control的代码
                table.remove(table.getSelectionIndices());
            }
        });
        // 创建查看菜单项
        MenuItem view = new MenuItem(menu, SWT.PUSH);
        view.setText("查看");
//        view.setImage(new Image(toolBar.getDisplay(), "icons//scope.gif"));
        // 为查看菜单项注册事件，当单击时打印出所选的姓名
        view.addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event event)
            {
                TableItem[] items = table.getSelection();
                for (int i = 0; i < items.length; i++)
                    System.out.print(items[i].getText());
            }
        });
 
        table.setMenu(menu);
    }

	private void renderTreeData(Tree tree,Display display) {
		
		Image[] images = new Image[] { display.getSystemImage(SWT.ICON_WARNING), display.getSystemImage(SWT.ICON_ERROR),
				display.getSystemImage(SWT.ICON_INFORMATION), display.getSystemImage(SWT.ICON_QUESTION) };

//		ImageData imageData = new ImageData("images/wjj.png");
//		imageData.width = 10;
//		imageData.height = 10;
//		Image image = new Image(tree.getDisplay(), "images/wjj.png");
//		Image image = new Image(display, imageData);
//		Rectangle bounds = image.getBounds();
//		GC gc = new GC(image);
//		gc.drawImage(image, 0, 0, 1, 1, 0, 0, 1, 1);
//		gc.dispose();
//		Image[] images = new Image[images1.length];
//		//设置图标大小
//		for (int i=0; i<images1.length; i++) {
//			ImageData imageData = images1[i].getImageData();
//			imageData.width = 1;
//			imageData.height = 1;
//			Image image2 = new Image(tree.getDisplay(), imageData);
//			images[i] = image2;
//		}
		for (int i = 0; i <4 ; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText("子功能节点 " + i);
//			item.setImage(images[i % images.length]);
//			item.setImage(image);
//			mapping.put("子功能节点 " + i, images[i % images.length]);
//			mapping.put("子功能节点 " + i, image);
		}
		
		//设置树为可编辑
//		TreeItem[] items = tree.getItems();
//		for (TreeItem treeItem : items) {
//			TreeEditor treeEditor = new TreeEditor(tree);
//			Text text = new Text(tree, SWT.NONE);
//			text.setText(treeItem.getText());
//			treeEditor.setEditor(text,treeItem);
//		}

		initPopup(tree);
		initDnD(display, tree);
		
		TreeEditor treeEditor = new TreeEditor(tree);
		treeEditor.horizontalAlignment = SWT.LEFT;
		treeEditor.grabHorizontal = true;
		treeEditor.minimumWidth = 30;
		
		tree.addMouseListener(
				new MouseAdapter() {
		            @Override
		            public void mouseDoubleClick(MouseEvent e) {
		                Point p = new Point(e.x, e.y);
		                // 通过点击位置的y坐标和第一列的任意x坐标获取当前行项目，其他列对应的item为null
		//                final TreeItem item = getCurrentItem(tree, e.y);
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
			//                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
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
		//	                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
			                    treeEditor.setEditor(combo, item, currentCol);
			                    break;
		
		                }
		                setChildrenCheck(item);
		
		            }

        });

		// 为树创建5列
		for (int i = 0; i < 2; i++) {
			TreeColumn column = new TreeColumn(tree, SWT.NONE);
			column.setText("column" + i);
		}
		for (int i = 0; i < tree.getColumnCount(); i++)
			tree.getColumn(i).pack();
		// 设置网格线可见
		tree.setLinesVisible(false);
		// 设置表头可见
		tree.setHeaderVisible(false);
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

    /**
     * 根据点击位置获取当前treeItem
     * 
     * @param tree
     * @param clickY
     *            点击位置的y坐标
     * @return
     */
    /*private TreeItem getCurrentItem(Tree tree, int clickY) {

        TreeItem item = null;

        int firstColWidth = tree.getColumn(0).getWidth();

        for (int i = 0; i < firstColWidth; i++) {

            item = tree.getItem(new Point(i, clickY));

            if (item != null)

                return item;

        }

        return item;

    }*/

    /**
     * 子条目复选框跟随父条目复选框状态
     * 
     * @param parent
     */
    private void setChildrenCheck(TreeItem parent) {
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
	
	
	public void initPopup(Tree tree){
        
        tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {

				Menu menu=new Menu(tree);
		        MenuItem addNode=new MenuItem(menu,SWT.PUSH);
		        addNode.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						
						if(addNode == arg0.widget) {
							TreeItem item = tree.getItem(new Point(e.x,e.y));
							if(null == item) {
								TreeItem ti = new TreeItem(tree, SWT.NONE);
								ti.setText("Item " );
							}else {
								TreeItem ti = new TreeItem(item, SWT.LEFT);
								
								ti.setText("Item " );
							}
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
		        addNode.setText("添加节点");
		        tree.setMenu(menu);
				
			}
			
			private TreeEditor treeEditor = new TreeEditor(tree);
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
//				Point p = new Point(e.x, e.y);
//                // 通过点击位置的y坐标和第一列的任意x坐标获取当前行项目，其他列对应的item为null
//                final TreeItem item =  tree.getItem(p);
//                if (item == null)
//                    return;
//                Control old = treeEditor.getEditor();
//                if (old != null) {
//                    old.dispose();
//                }
//                final int currentCol = getColId(tree, item, p);
//                final Text text = new Text(tree, SWT.RIGHT);
////                final Combo combo = new Combo(tree, SWT.READ_ONLY);
//                switch (2) {
//	                case 0:
//	                case 1:
//	                case 2:
////	                    text.setForeground(item.getForeground());
//	                    text.setText(item.getText());
//	//                    text.selectAll();
//	//                    text.setFocus();
//	                    text.addModifyListener(new ModifyListener() {
//	                        @Override
//	                        public void modifyText(ModifyEvent e) {
//	                            item.setText(currentCol,text.getText());
//	                        }
//	                    });
//	//                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
//	                    treeEditor.setEditor(text, item, currentCol);
//	                    break;
//	                case 3:
//	//                    combo.setForeground(item.getForeground());
//	//                    combo.add("借");
//	//                    combo.add("贷");
//	//                    combo.setText(item.getText(currentCol));
//	//                    combo.setFocus();
//	//                    combo.addSelectionListener(new SelectionAdapter() {
//	//                        public void widgetSelected(SelectionEvent e) {
//	//                            item.setText(currentCol, combo.getText());
//	//                        }
//	//                    });
//	//                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
//	//                    treeEditor.setEditor(combo, item, currentCol);
//	                    break;
//                }
////                setChildrenCheck(item);

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
        
//        MenuItem deleteNode=new MenuItem(menu, SWT.PUSH);
//        deleteNode.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//        deleteNode.setText("删除节点");
        
        
//        tree.setMenu(menu);
        
//        TreeItem[] items = tree.getItems();
//        for (TreeItem treeItem : items) {
//			TreeEditor treeEditor = new TreeEditor(tree);
//			Text text = new Text(tree, SWT.NONE);
//			text.setText(treeItem.getText());
//			treeEditor.setEditor(text,treeItem,1);
//		}
    }
	
	public void initMouseListener(Tree tree){
		tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private static void initDnD(final Display display, final Tree tree) {
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
		
		
}
