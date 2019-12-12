package com.home.swt.layout.item.comp;

import java.util.Hashtable;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
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

import com.home.swt.SwtUtils;
import com.home.swt.example.SwtExap4.TableItemControls;
import com.home.swt.layout.item.model.Person;
import com.home.swt.layout.item.model.PersonContextProvider;
import com.home.swt.layout.item.model.PersonTableLableProvider;
import com.home.swt.tableview.item1.ContextProvider;
import com.home.swt.tableview.item1.Data;
import com.home.swt.tableview.item1.tableLableProvider;

public class RightComp {

	private Composite rightComposite;
	private Shell parent;
	private TableEditor tableEditor;
	private Hashtable<TableItem, Control> tablecontrols = new Hashtable<TableItem, Control>();
	
	public RightComp(Shell parent) {
		this.parent = parent;
		rightComposite = new Composite(parent, SWT.BORDER);
		rightComposite.setLayout(new GridLayout(1,false));
		GridData gridData1 = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		gridData1.widthHint = 500;
		rightComposite.setLayoutData(gridData1);
	}

	public void setTreeListener(Tree tree) {
		//���ѡ������¼�
		tree.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti = (TreeItem)arg0.item;
				//�����ʾ�����е����е����
				Control[] children = rightComposite.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				if(ti.getText().equalsIgnoreCase("�ӹ��ܽڵ� 1")) {
					//���� ��Ŀؼ�
					createTableControllers(rightComposite);
				}
				if(ti.getText().equalsIgnoreCase("�ӹ��ܽڵ� 2")) {
					//���� ѡ��Ŀؼ����
					createTabControllers(rightComposite);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void createTableControllers(Composite composite) {
		createToolBarAndTable(composite);
	}
	
	// ����������
    private void createToolBarAndTable(Composite composite) {

    	ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
    	toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        final ToolItem add = new ToolItem(toolBar, SWT.PUSH);
//        add.setText("���");
        add.setImage(SwtUtils.getimage("images/addIcon.png", toolBar.getDisplay()));
        final ToolItem del = new ToolItem(toolBar, SWT.PUSH);
//        del.setText("ɾ��");
        del.setImage(SwtUtils.getimage("images/delIcon.png", toolBar.getDisplay()));
        final ToolItem back = new ToolItem(toolBar, SWT.PUSH);
        back.setText("����");
//        back.setImage(new Image(toolBar.getDisplay(), "icons//up.gif"));
        final ToolItem forward = new ToolItem(toolBar, SWT.PUSH);
        forward.setText("����");
//        forward.setImage(new Image(toolBar.getDisplay(), "icons//down.gif"));
        final ToolItem save = new ToolItem(toolBar, SWT.PUSH);
        save.setText("����");
//        save.setImage(new Image(toolBar.getDisplay(), "icons//save.gif"));
        Table table = createTable(composite);
        // ��������ť�¼�����
        Listener listener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                // ���������Ӱ�ť�����һ�У���ʵ�ʵ���Ŀʵ����ͨ���ǽ�������Ĳ������������
                // ����Ϊ�˼��������ӹ̶���һ����¼
                if (event.widget == add) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{"֣��", "Ů", "13"});
                    Text setTableItemEditor = setTableItemEditor(table, item, 0);
                    tablecontrols.put(item, setTableItemEditor);
                }
                // �������ɾ����ť
                else if (event.widget == del) {
                    // ���Ȼ�ñ�������е���
                    TableItem[] items = table.getItems();
                    // ѭ��������
                    for (int i = items.length - 1; i >= 0; i--) {
                        // �������û�б�ѡ�У�����ѭ��
                        if (!items[i].getChecked())
                            continue;
                        // ����ѡ�У����Ҹñ�����Ƿ��и���
                        int index = table.indexOf(items[i]);
                        // ���û�и��У�����ѭ��
                        if (index < 0)
                            continue;
                        table.remove(index);
                        Control control = tablecontrols.get(items[i]);
                        if(null != control) {
                        	control.dispose();
                        }
                        System.out.println("i=" + i + ", index=" + index);
                        System.out.println("����:" + table.getItemCount());
//                        table.pack();
                        MessageBox messageBox = new MessageBox(parent);
                        messageBox.setMessage("ɾ����ʾ");
                        messageBox.setMessage("ɾ���ɹ�������");
                        messageBox.open();
                        
                    }
                }
                // ����������ư�ť
                else if (event.widget == back) {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > 0)
                        table.setSelection(selectedRow - 1);// ����ѡ�е�����
                }
                // ����������ư�ť
                else if (event.widget == forward) {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > -1
                            && selectedRow < table.getItemCount() - 1)
                        table.setSelection(selectedRow + 1);// ����ѡ�е�����
                }
                // ����������水ť
                else if (event.widget == save) {
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
        
        createMenu(table);
        composite.layout();

    }

    private Text setTableItemEditor(Table table, TableItem item,int column) {
    	tableEditor = new TableEditor(table);
//			tableEditor.horizontalAlignment = SWT.RIGHT;
		Text text = new Text(table, SWT.NONE);
		text.setText(item.getText(0));
		tableEditor.grabHorizontal = true;
		//�����
		tableEditor.setEditor(text,item,0);
		//����text�������޸ļ����¼�
		text.addModifyListener(new ModifyListener() {
		  public void modifyText(ModifyEvent e) {
			  tableEditor.getItem().setText(column, text.getText());
		  }
		});
		return text;
	}
	
//    private Table createTable(Composite composite) {
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
//		
//		TableItem item = new TableItem(table, SWT.NONE);
//        item.setText(new String[]{"֣��1", "Ů1", "5681"});
//        setTableItemEditor(table, item, 0);
//		return table;
//		
//	}
    private Table createTable(Composite composite) {
    	TableViewer tableViewer = new TableViewer(composite,SWT.NONE);
//    	CheckboxTableViewer tableViewer = new CheckboxTableViewer(composite,SWT.NONE);
    	Table table = tableViewer.getTable();
    	table.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
    	table.setLinesVisible(true);
    	table.setHeaderVisible(true);
    	TableLayout tableLayout = new TableLayout();
    	table.setLayout(tableLayout);
    	tableLayout.addColumnData(new ColumnWeightData(30));
    	
    	new TableColumn(table, SWT.NONE).setText("����");
    	tableLayout.addColumnData(new ColumnWeightData(20));
    	
    	new TableColumn(table, SWT.NONE).setText("�Ա�");
    	tableLayout.addColumnData(new ColumnWeightData(5));
    	
    	new TableColumn(table, SWT.NONE).setText("����");
    	tableLayout.addColumnData(new ColumnWeightData(5));
    	
    	// ����������
		tableViewer.setContentProvider(new PersonContextProvider());
		// ���ñ�ǩ��
		tableViewer.setLabelProvider(new PersonTableLableProvider());
		// �����ݼ��ϸ�tableView
		tableViewer.setInput(new Person().addList());
		//���ñ༭��
		TableItem[] items = table.getItems();
		for (TableItem tableItem : items) {
			Text setTableItemEditor = setTableItemEditor(table, tableItem, 0);
			tablecontrols.put(tableItem, setTableItemEditor);
		}
    	return table;
    	
    }

    private void createTabControllers(Composite composite) {
//		final Label label = new Label(composite, SWT.NONE);
//		label.setLayoutData(new GridData(SWT.LEFT,SWT.CENTER,false,false));
//		label.setText("����A");
		
		//����ѡ����
		final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tabFolder.setLayout(new GridLayout(5,false));
		
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("�ӹ���1");
		//���ӹ���
		Composite tabItem1Composite = new Composite(tabFolder, SWT.NONE);
		tabItem1Composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tabItem1Composite.setLayout(new GridLayout(5,false));
		//���ÿ�ݲ˵�
		ToolBar toolBar = new ToolBar(tabItem1Composite, SWT.BORDER);
		toolBar.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,true,false,5,1));
		toolBar.setBackground(new Color(composite.getDisplay(), 125,185,195));
		ToolItem saveTool = new ToolItem(toolBar, SWT.BORDER);
		saveTool.setText("����");
		
		StyledText textEditor = new StyledText(tabItem1Composite, SWT.BORDER);
		textEditor.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
		textEditor.setText("���ǡ�������");
		//���ù���1�Ŀؼ�
		tabItem1.setControl(tabItem1Composite);
		
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("�ӹ���2");
		
		Composite tabItem2Composite = new Composite(tabFolder, SWT.NONE);
		tabItem2Composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tabItem2Composite.setLayout(new GridLayout(2,false));
		
		tabItem2.setControl(tabItem2Composite);
		
		Label nameLab = new Label(tabItem2Composite, SWT.NONE);
		nameLab.setText("������");
		Text nameText = new Text(tabItem2Composite, SWT.BORDER);
		
		Label sexLab = new Label(tabItem2Composite, SWT.NONE);
		sexLab.setText("�Ա�");
		Text sexText = new Text(tabItem2Composite, SWT.BORDER);
		
		Label ageLab = new Label(tabItem2Composite, SWT.NONE);
		ageLab.setText("���䣺");
		Text ageText = new Text(tabItem2Composite, SWT.BORDER);
		
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("�ӹ���3");
		
		composite.layout();
		
	}
    
    // ���������Ĳ˵�
    private void createMenu(Table table){
        // ��������ʽ�˵�
    	Menu menu = new Menu(parent, SWT.POP_UP);
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
}
