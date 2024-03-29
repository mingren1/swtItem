package com.home.swt.tree.tr1;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class TreeChildNode {
	private static Map<String, Image> mapping = new HashMap<String, Image>();

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setText("StackOverflow");
		shell.setLayout(new FillLayout());

		Tree tree = new Tree(shell, SWT.NONE);

		Image[] images = new Image[] { display.getSystemImage(SWT.ICON_WARNING), display.getSystemImage(SWT.ICON_ERROR),
				display.getSystemImage(SWT.ICON_INFORMATION), display.getSystemImage(SWT.ICON_QUESTION) };

		for (int i = 0; i < 10; i++) {
			TreeItem item = new TreeItem(tree, SWT.NONE);
			item.setText("Item " + i);
			item.setImage(images[i % images.length]);
			mapping.put("Item " + i, images[i % images.length]);
		}

		initPopup(tree);
		
		initDnD(display, tree);
		
		
		
//		final TreeViewer treeViewer = new TreeViewer(shell, SWT.BORDER);
//        tree = treeViewer.getTree();
//        
//        tree.setBounds(0, 10, 192, 354);
//        treeViewer.setContentProvider(new TreeContentProvider());
//        treeViewer.setLabelProvider(new TreeLabelProvider());
        
//        initPopup(tree);   //主要是这个函数，将tree生成后，实现这个函数就行了。
		
		
		
		

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
	
	public static void initPopup(Tree tree){
//        Menu menu=new Menu(tree);
//        MenuItem newItem=new MenuItem(menu,SWT.PUSH);
//        newItem.setText("新增部门");
//        MenuItem newMemberItem=new MenuItem(menu, SWT.PUSH);
//        
//        newMemberItem.setText("新增员工");
//        MenuItem editItem=new MenuItem(menu,SWT.PUSH);
//        editItem.setText("编辑");
//        MenuItem deleteItem=new MenuItem(menu, SWT.PUSH);
//        
//        deleteItem.setText("删除");
//        tree.setMenu(menu);
		
		
		
		
		tree.addMouseListener(new MouseListener() {
		    
		     @Override
		     public void mouseDown(MouseEvent e) {
		     TreeItem item=tree.getItem(new Point(e.x,e.y));
		     if(e.button==3){ //右键
		     if(item==null){//在tree上添加子节点
		     TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		     treeItem.setText("Item  Tree" );
		     }else{//在item上添加子节点
		     TreeItem treeItem = new TreeItem(item, SWT.NONE);
		     treeItem.setText("Item " );
		     }
		     }
		     }

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		    });
		
//		tree.addMouseListener(new MouseListener() {
//			
//			@Override
//			public void mouseUp(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//			
//			@Override
//			public void mouseDown(MouseEvent e) {
//			     TreeItem item=tree.getItem(new Point(e.x,e.y));
//			     if(e.button == 3) {
//			    	Menu menu = new Menu(tree);
//			        MenuItem newItem=new MenuItem(menu,SWT.PUSH);
//			        newItem.setText("新增节点*");
//			        tree.layout();
////			        newItem.addSelectionListener(new SelectionListener() {
////						
////						@Override
////						public void widgetSelected(SelectionEvent arg0) {
////							if(newItem == arg0.widget) {
////								//新增节点
////								TreeItem ti = new TreeItem(item, SWT.NONE);
////								ti.setText("我是新增的节点");
////							}
////						}
////						
////						@Override
////						public void widgetDefaultSelected(SelectionEvent arg0) {
////							// TODO Auto-generated method stub
////							
////						}
////					});
//			     }
//				
//			}
//			
//			@Override
//			public void mouseDoubleClick(MouseEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
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
