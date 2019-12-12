package com.home.swt.tree;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class LeftCom extends Composite{

	private Tree tree;
	public LeftCom(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());
		this.tree = new Tree(parent, SWT.BORDER);
		TreeItem ti = new TreeItem(tree, SWT.NONE);
		ti.setText("�����ܽڵ�");
		TreeItem ti_a = new TreeItem(ti, SWT.NONE);
		ti_a.setText("���ܽڵ�A");
		TreeItem ti_b = new TreeItem(ti, SWT.NONE);
		ti_b.setText("���ܽڵ�B");
		TreeItem ti_c = new TreeItem(ti, SWT.NONE);
		ti_c.setText("���ܽڵ�C");
		//Ĭ��չ��
		ti.setExpanded(true);
		
		tree.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem item = (TreeItem)arg0.item;
				//�ͷ��ұ߹������������
				RightCom right = MainApp.right;
				Control[] children = right.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				if(item.getText().equalsIgnoreCase("���ܽڵ�A")) {
					//��ʾ����A���������
					
					RightComA rigthComA = new RightComA(right,SWT.NONE);
					rigthComA.layout();
				}
			}
//			
//			@Override
//			public void widgetDefaultSelected(SelectionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
		});
		
		/*tree.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			private TreeEditor treeEditor = new TreeEditor(tree);
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				Point p = new Point(e.x, e.y);
                // ͨ�����λ�õ�y����͵�һ�е�����x�����ȡ��ǰ����Ŀ�������ж�Ӧ��itemΪnull
                final TreeItem item =  tree.getItem(p);
                if (item == null)
                    return;
                Control old = treeEditor.getEditor();
                if (old != null) {
                    old.dispose();
                }
                int currentCol = getColId(tree, item, p);
                Text text = new Text(tree, SWT.RIGHT);
//                final Combo combo = new Combo(tree, SWT.READ_ONLY);
                switch (2) {
	                case 0:
	                case 1:
	                case 2:
//	                    text.setForeground(item.getForeground());
	                    text.setText(item.getText());
	//                    text.selectAll();
	//                    text.setFocus();
	                    text.addModifyListener(new ModifyListener() {
	                        @Override
	                        public void modifyText(ModifyEvent e) {
	                            item.setText(currentCol,text.getText());
	                        }
	                    });
	//                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
	                    treeEditor.setEditor(text, item, currentCol);
	                    break;
	                case 3:
	//                    combo.setForeground(item.getForeground());
	//                    combo.add("��");
	//                    combo.add("��");
	//                    combo.setText(item.getText(currentCol));
	//                    combo.setFocus();
	//                    combo.addSelectionListener(new SelectionAdapter() {
	//                        public void widgetSelected(SelectionEvent e) {
	//                            item.setText(currentCol, combo.getText());
	//                        }
	//                    });
	//                    treeEditor.minimumWidth = item.getBounds(currentCol).width;
	//                    treeEditor.setEditor(combo, item, currentCol);
	                    break;
                }
//                setChildrenCheck(item);
				
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
		});*/
		
		
		
		
		
		
	}
	
	
	 // ��ȡ��ǰ�кŵķ���

    public Tree getTree() {
		return tree;
	}


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
     * ����Ŀ��ѡ����游��Ŀ��ѡ��״̬
     * 
     * @param parent
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
	
//	@Override
//	protected void checkSubclass() {
//		// TODO Auto-generated method stub
//		super.checkSubclass();
//	}

}
