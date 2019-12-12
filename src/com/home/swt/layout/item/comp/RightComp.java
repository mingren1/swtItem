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
		//添加选择监听事件
		tree.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TreeItem ti = (TreeItem)arg0.item;
				//清除显示构件中的所有的组件
				Control[] children = rightComposite.getChildren();
				for (Control control : children) {
					control.dispose();
				}
				if(ti.getText().equalsIgnoreCase("子功能节点 1")) {
					//创建 表的控件
					createTableControllers(rightComposite);
				}
				if(ti.getText().equalsIgnoreCase("子功能节点 2")) {
					//创建 选项卡的控件组合
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
	
	// 创建工具栏
    private void createToolBarAndTable(Composite composite) {

    	ToolBar toolBar = new ToolBar(composite, SWT.FLAT);
    	toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        final ToolItem add = new ToolItem(toolBar, SWT.PUSH);
//        add.setText("添加");
        add.setImage(SwtUtils.getimage("images/addIcon.png", toolBar.getDisplay()));
        final ToolItem del = new ToolItem(toolBar, SWT.PUSH);
//        del.setText("删除");
        del.setImage(SwtUtils.getimage("images/delIcon.png", toolBar.getDisplay()));
        final ToolItem back = new ToolItem(toolBar, SWT.PUSH);
        back.setText("上移");
//        back.setImage(new Image(toolBar.getDisplay(), "icons//up.gif"));
        final ToolItem forward = new ToolItem(toolBar, SWT.PUSH);
        forward.setText("下移");
//        forward.setImage(new Image(toolBar.getDisplay(), "icons//down.gif"));
        final ToolItem save = new ToolItem(toolBar, SWT.PUSH);
        save.setText("保存");
//        save.setImage(new Image(toolBar.getDisplay(), "icons//save.gif"));
        Table table = createTable(composite);
        // 工具栏按钮事件处理
        Listener listener = new Listener() {
            @Override
            public void handleEvent(Event event) {
                // 如果单击添加按钮，添加一行，在实际的项目实现中通常是接收输入的参数，案后添加
                // 这里为了简单起见，添加固定的一条记录
                if (event.widget == add) {
                    TableItem item = new TableItem(table, SWT.NONE);
                    item.setText(new String[]{"郑六", "女", "13"});
                    Text setTableItemEditor = setTableItemEditor(table, item, 0);
                    tablecontrols.put(item, setTableItemEditor);
                }
                // 如果单击删除按钮
                else if (event.widget == del) {
                    // 首先获得表格中所有的行
                    TableItem[] items = table.getItems();
                    // 循环所有行
                    for (int i = items.length - 1; i >= 0; i--) {
                        // 如果该行没有被选中，继续循环
                        if (!items[i].getChecked())
                            continue;
                        // 否则选中，查找该表格中是否有该行
                        int index = table.indexOf(items[i]);
                        // 如果没有该行，继续循环
                        if (index < 0)
                            continue;
                        table.remove(index);
                        Control control = tablecontrols.get(items[i]);
                        if(null != control) {
                        	control.dispose();
                        }
                        System.out.println("i=" + i + ", index=" + index);
                        System.out.println("行数:" + table.getItemCount());
//                        table.pack();
                        MessageBox messageBox = new MessageBox(parent);
                        messageBox.setMessage("删除提示");
                        messageBox.setMessage("删除成功！！！");
                        messageBox.open();
                        
                    }
                }
                // 如果单击上移按钮
                else if (event.widget == back) {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > 0)
                        table.setSelection(selectedRow - 1);// 设置选中的行数
                }
                // 如果单击下移按钮
                else if (event.widget == forward) {
                    int selectedRow = table.getSelectionIndex();
                    if (selectedRow > -1
                            && selectedRow < table.getItemCount() - 1)
                        table.setSelection(selectedRow + 1);// 设置选中的行数
                }
                // 如果单击保存按钮
                else if (event.widget == save) {
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
        
        createMenu(table);
        composite.layout();

    }

    private Text setTableItemEditor(Table table, TableItem item,int column) {
    	tableEditor = new TableEditor(table);
//			tableEditor.horizontalAlignment = SWT.RIGHT;
		Text text = new Text(table, SWT.NONE);
		text.setText(item.getText(0));
		tableEditor.grabHorizontal = true;
		//组件绑定
		tableEditor.setEditor(text,item,0);
		//增加text的内容修改监听事件
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
//		tableColumn1.setText("姓名");
//		TableColumn tableColumn2 = new TableColumn(table,SWT.NONE);
//		tableColumn2.setWidth(100);
//		tableColumn2.setText("性别");
//		TableColumn tableColumn3 = new TableColumn(table,SWT.NONE);
//		tableColumn3.setWidth(100);
//		tableColumn3.setText("年龄");
//		
//		TableItem item = new TableItem(table, SWT.NONE);
//        item.setText(new String[]{"郑六1", "女1", "5681"});
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
    	
    	new TableColumn(table, SWT.NONE).setText("姓名");
    	tableLayout.addColumnData(new ColumnWeightData(20));
    	
    	new TableColumn(table, SWT.NONE).setText("性别");
    	tableLayout.addColumnData(new ColumnWeightData(5));
    	
    	new TableColumn(table, SWT.NONE).setText("年龄");
    	tableLayout.addColumnData(new ColumnWeightData(5));
    	
    	// 设置内容器
		tableViewer.setContentProvider(new PersonContextProvider());
		// 设置标签器
		tableViewer.setLabelProvider(new PersonTableLableProvider());
		// 把数据集合给tableView
		tableViewer.setInput(new Person().addList());
		//设置编辑器
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
//		label.setText("功能A");
		
		//设置选项卡组件
		final TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tabFolder.setLayout(new GridLayout(5,false));
		
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("子功能1");
		//增加构件
		Composite tabItem1Composite = new Composite(tabFolder, SWT.NONE);
		tabItem1Composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tabItem1Composite.setLayout(new GridLayout(5,false));
		//设置快捷菜单
		ToolBar toolBar = new ToolBar(tabItem1Composite, SWT.BORDER);
		toolBar.setLayoutData(new GridData(SWT.FILL,SWT.LEFT,true,false,5,1));
		toolBar.setBackground(new Color(composite.getDisplay(), 125,185,195));
		ToolItem saveTool = new ToolItem(toolBar, SWT.BORDER);
		saveTool.setText("保存");
		
		StyledText textEditor = new StyledText(tabItem1Composite, SWT.BORDER);
		textEditor.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true,5,1));
		textEditor.setText("我是。。。。");
		//设置功能1的控件
		tabItem1.setControl(tabItem1Composite);
		
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("子功能2");
		
		Composite tabItem2Composite = new Composite(tabFolder, SWT.NONE);
		tabItem2Composite.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		tabItem2Composite.setLayout(new GridLayout(2,false));
		
		tabItem2.setControl(tabItem2Composite);
		
		Label nameLab = new Label(tabItem2Composite, SWT.NONE);
		nameLab.setText("姓名：");
		Text nameText = new Text(tabItem2Composite, SWT.BORDER);
		
		Label sexLab = new Label(tabItem2Composite, SWT.NONE);
		sexLab.setText("性别：");
		Text sexText = new Text(tabItem2Composite, SWT.BORDER);
		
		Label ageLab = new Label(tabItem2Composite, SWT.NONE);
		ageLab.setText("年龄：");
		Text ageText = new Text(tabItem2Composite, SWT.BORDER);
		
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("子功能3");
		
		composite.layout();
		
	}
    
    // 创建上下文菜单
    private void createMenu(Table table){
        // 创建弹出式菜单
    	Menu menu = new Menu(parent, SWT.POP_UP);
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
}
