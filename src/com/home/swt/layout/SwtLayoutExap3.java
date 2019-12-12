package com.home.swt.layout;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import org.eclipse.swt.widgets.TreeItem;

public class SwtLayoutExap3 {

	protected Shell shell;
	private static Map<String, Image> mapping = new HashMap<String, Image>();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SwtLayoutExap3 window = new SwtLayoutExap3();
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
		shell.setText("SWT ������");
		shell.setLayout(new GridLayout(5,false));
		//���ÿ��ѡ�
		Composite shortcutTabComp = new Composite(shell, SWT.NONE);
		shortcutTabComp.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData shortcutGridData = new GridData(SWT.LEFT, SWT.CENTER, true, false, 5, 1);
		shortcutTabComp.setLayoutData(shortcutGridData);
		ToolBar mainToolBar = new ToolBar(shortcutTabComp, SWT.FLAT);
		ToolItem toolItem1 = new ToolItem(mainToolBar, SWT.PUSH);
//		toolItem1.setText("����1");
		ImageData imageData = new ImageData("C:/Users/42194/Desktop/Capture001.png");
		imageData.width = 15;
		imageData.height = 15;
		final Image image = new Image(mainToolBar.getDisplay(), imageData);
		toolItem1.setImage(image);
		ToolItem toolItem2 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem2.setText("����2");
		ToolItem toolItem3 = new ToolItem(mainToolBar, SWT.NONE);
		toolItem3.setText("����3");
		
		
		Composite composite_0 = new Composite(shell, SWT.NONE);
		composite_0.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gridData0 = new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1);
		composite_0.setLayoutData(gridData0);
		Tree tree = new Tree(composite_0, SWT.BORDER);
		//��Ⱦ���ڵ�����
		renderTreeData(tree,display);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(1,false));
		GridData gridData1 = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		gridData1.widthHint = 500;
		composite_1.setLayoutData(gridData1);
		
		//���������ӽڵ�ļ����¼�
		setTreeListener(tree,composite_1);
		

	}

	private void setTreeListener(Tree tree,Composite composite) {
		//���ѡ������¼�
		tree.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti = (TreeItem)arg0.item;
				//�����ʾ�����е����е����
				Control[] children = composite.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				if(ti.getText().equalsIgnoreCase("�ӹ��ܽڵ� 1")) {
					//���� ��Ŀؼ�
					createTableControllers(composite);
				}
				if(ti.getText().equalsIgnoreCase("�ӹ��ܽڵ� 2")) {
					//���� ѡ��Ŀؼ����
					createTabControllers(composite);
				}
//				if(ti.getText().equalsIgnoreCase("�ӹ��ܽڵ�1")) {
//					Table table = new Table(composite, SWT.BORDER);
//					//���� ��Ŀؼ�
//					createTableControllers(table);
//				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//����ƶ������¼�
		
	}

	protected void createTabControllers(Composite composite) {
		final Label label = new Label(composite, SWT.NONE);
		label.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false));
		label.setText("����A");
		
		//����ѡ����
		final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("�ӹ���1");
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("�ӹ���2");
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("�ӹ���3");
		
		composite.layout();
		
	}

	protected void createTableControllers(Composite composite) {
		createToolBarAndTable(composite);
//		createTableHeader(composite);
		
	}

//	private void createTableHeader(Composite composite) {
//		Table table = new Table(composite, SWT.BORDER);
//		table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
//		TableColumn tableColumn1 = new TableColumn(table,SWT.NONE);
//		tableColumn1.setWidth(100);
//		tableColumn1.setText("����");
//		TableColumn tableColumn2 = new TableColumn(table,SWT.NONE);
//		tableColumn2.setWidth(100);
//		tableColumn2.setText("�Ա�");
//		TableColumn tableColumn3 = new TableColumn(table,SWT.NONE);
//		tableColumn3.setWidth(100);
//		tableColumn3.setText("����");
//	}
	// ����������
    private void createToolBarAndTable(Composite composite) {
    	ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
    	toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        final ToolItem add = new ToolItem(toolBar, SWT.PUSH);
        add.setText("���");
//        add.setImage(new Image(toolBar.getDisplay(), "icons//add.gif"));
        final ToolItem del = new ToolItem(toolBar, SWT.PUSH);
        del.setText("ɾ��");
//        del.setImage(new Image(toolBar.getDisplay(), "icons//delete.gif"));
        final ToolItem back = new ToolItem(toolBar, SWT.PUSH);
        back.setText("����");
//        back.setImage(new Image(toolBar.getDisplay(), "icons//up.gif"));
        final ToolItem forward = new ToolItem(toolBar, SWT.PUSH);
        forward.setText("����");
//        forward.setImage(new Image(toolBar.getDisplay(), "icons//down.gif"));
        final ToolItem save = new ToolItem(toolBar, SWT.PUSH);
        save.setText("����");
//        save.setImage(new Image(toolBar.getDisplay(), "icons//save.gif"));
        Table table = new Table(composite, SWT.BORDER);
		table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		TableColumn tableColumn1 = new TableColumn(table,SWT.NONE);
		tableColumn1.setWidth(100);
		tableColumn1.setText("����");
		TableColumn tableColumn2 = new TableColumn(table,SWT.NONE);
		tableColumn2.setWidth(100);
		tableColumn2.setText("�Ա�");
		TableColumn tableColumn3 = new TableColumn(table,SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("����");
		
		TableItem item = new TableItem(table, SWT.NONE);
        item.setText(new String[]{"֣��1", "Ů1", "5681"});
     // ��������ť�¼�����
        Listener listener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                // ���������Ӱ�ť�����һ�У���ʵ�ʵ���Ŀʵ����ͨ���ǽ�������Ĳ������������
                // ����Ϊ�˼��������ӹ̶���һ����¼
                if (event.widget == add)
                {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{"֣��", "Ů", "568"});
                }
                // �������ɾ����ť
                else if (event.widget == del)
                {
                    // ���Ȼ�ñ�������е���
                    TableItem[] items = table.getItems();
                    // ѭ��������
                    for (int i = items.length - 1; i >= 0; i--)
                    {
                        // �������û�б�ѡ�У�����ѭ��
                        if (!items[i].getChecked())
                            continue;
                        // ����ѡ�У����Ҹñ�����Ƿ��и���
                        int index = table.indexOf(items[i]);
                        // ���û�и��У�����ѭ��
                        if (index < 0)
                            continue;
                        // ɾ���󶨵Ŀؼ�
//                        TableItemControls cons = tablecontrols.get(items[index]);
//                        if (cons != null)
//                        {
//                            cons.dispose();
//                            tablecontrols.remove(items[index]);
//                            System.out.println("dispose " + index);
//                        }
                        // ����и��У�ɾ������
                        // items[index].dispose();
                        table.remove(index);
                        System.out.println("i=" + i + ", index=" + index);
                        System.out.println("����:" + table.getItemCount());
                        // table.pack();
                    }
                }
                // ����������ư�ť
                else if (event.widget == back)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > 0)
                        table.setSelection(selectedRow - 1);// ����ѡ�е�����
                }
                // ����������ư�ť
                else if (event.widget == forward)
                {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > -1
                            && selectedRow < table.getItemCount() - 1)
                        table.setSelection(selectedRow + 1);// ����ѡ�е�����
                }
                // ����������水ť
                else if (event.widget == save)
                {
                    TableItem[] items = table.getItems();
                    // ���浽�ļ������ݿ��У����ݳ־û�������ʡ��
                    for (int i = 0; i < items.length; i++)
                        for (int j = 0; j < table.getColumnCount(); j++)
                            System.out.println(items[i].getText(j));
                }
            }

        };
        // Ϊ�������İ�ťע���¼�
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
			//�����
			tableEditor.setEditor(text,items[i],0);
			//����text�������޸ļ����¼�
			text.addModifyListener(new ModifyListener() {
	          public void modifyText(ModifyEvent e) {
	        	  tableEditor.getItem().setText(0, text.getText());
	          }
	        });
			
		}
        
        createMenu(table);
        composite.layout();

    }
    
 // ���������Ĳ˵�
    private void createMenu(Table table){
        // ��������ʽ�˵�
    	Menu menu = new Menu(shell, SWT.POP_UP);
        // ���øò˵�Ϊ���˵�
//        table.setMenu(menu);
        // ����ɾ���˵���
        MenuItem del = new MenuItem(menu, SWT.PUSH);
        del.setText("ɾ��");
//        del.setImage(new Image(toolBar.getDisplay(), "icons//delete.gif"));
        // Ϊɾ���˵�ע���¼���������ʱ��ɾ����ѡ�����
        del.addListener(SWT.Selection, new Listener()
        {
            public void handleEvent(Event event)
            {
                // �˴������ɾ����Control�Ĵ���
                table.remove(table.getSelectionIndices());
            }
        });
        // �����鿴�˵���
        MenuItem view = new MenuItem(menu, SWT.PUSH);
        view.setText("�鿴");
//        view.setImage(new Image(toolBar.getDisplay(), "icons//scope.gif"));
        // Ϊ�鿴�˵���ע���¼���������ʱ��ӡ����ѡ������
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

		for (int i = 0; i <4 ; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText("�ӹ��ܽڵ� " + i);
			item.setImage(images[i % images.length]);
			mapping.put("�ӹ��ܽڵ� " + i, images[i % images.length]);
		}

		initPopup(tree);
		initDnD(display, tree);

//		shell.pack();
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
	}
//	private boolean flag = false;
	public void initPopup(Tree tree){
////		boolean flag = false;
//        Menu menu=new Menu(tree);
//        MenuItem addNode=new MenuItem(menu,SWT.PUSH);
//        addNode.addSelectionListener(new SelectionListener() {
//			
//			@Override
//			public void widgetSelected(SelectionEvent arg0) {
////				if(addNode == arg0.widget) {
////					Menu parent = addNode.getParent();
////					Decorations parent2 = parent.getParent();
////					Cursor cursor = parent2.getCursor();
////					
////				}
////				TreeItem item = tree.getItem(new Point(arg0.x, arg0.y));
////				TreeItem ti = (TreeItem)arg0.item;
////				String text = ti.getText();
////				Tree parent = ti.getParent();
////				TreeItem treeItem = new TreeItem(parent, SWT.NONE);
//				
////				TreeItem treeItem = new TreeItem(tree, SWT.NONE);
////				treeItem.setText("�����ڵ�*");
//				
////				if(addNode == arg0.widget) {
////					flag = true;
////				}
//			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
//        addNode.setText("��ӽڵ�");
        
        tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
//				TreeItem item=tree.getItem(new Point(e.x,e.y));
//			     if(e.button==3){ //�Ҽ�
//				     if(item==null){//��tree������ӽڵ�
//					     TreeItem treeItem = new TreeItem(tree, SWT.NONE);
//					     treeItem.setText("Item  Tree" );
//				     }else {//��item������ӽڵ�B
//					     TreeItem treeItem = new TreeItem(item, SWT.NONE);
//					     treeItem.setText("Item " );
//				     }
//			     }
				
				
				Menu menu=new Menu(tree);
		        MenuItem addNode=new MenuItem(menu,SWT.PUSH);
		        addNode.addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
//						if(addNode == arg0.widget) {
//							Menu parent = addNode.getParent();
//							Decorations parent2 = parent.getParent();
//							Cursor cursor = parent2.getCursor();
//							
//						}
//						TreeItem item = tree.getItem(new Point(arg0.x, arg0.y));
//						TreeItem ti = (TreeItem)arg0.item;
//						String text = ti.getText();
//						Tree parent = ti.getParent();
//						TreeItem treeItem = new TreeItem(parent, SWT.NONE);
						
//						TreeItem treeItem = new TreeItem(tree, SWT.NONE);
//						treeItem.setText("�����ڵ�*");
						
						if(addNode == arg0.widget) {
							TreeItem item = tree.getItem(new Point(e.x,e.y));
							TreeItem ti = new TreeItem(item, SWT.NONE);
							ti.setText("Item " );
						}
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				});
		        addNode.setText("��ӽڵ�");
		        tree.setMenu(menu);
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
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
//        deleteNode.setText("ɾ���ڵ�");
        
        
//        tree.setMenu(menu);
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
