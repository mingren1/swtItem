package com.home.swt.tabfolder;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Spinner;

public class TabFolderExap1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TabFolderExap1 window = new TabFolderExap1();
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
		shell.setSize(649, 599);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 621, 362);
		composite.setLayout(null);
		
		TabFolder tabFolder = new TabFolder(composite, SWT.NONE);
		tabFolder.setBounds(36, 23, 278, 159);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("New Item");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tabItem.setControl(composite_1);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setBounds(10, 30, 90, 20);
		lblNewLabel.setText("New Labe2");
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBounds(10, 73, 90, 20);
		lblNewLabel_1.setText("New Label");
		Image image = new Image(shell.getDisplay(), "images/wjj.png");
		Rectangle bounds = image.getBounds();
		Image imageNew = new Image(shell.getDisplay(), 10, 40);
		GC gc = new GC(imageNew); 
		gc.drawImage(image, 0, 0, bounds.width, bounds.height, 0, 0, 10, 40);
		gc.dispose();
		
		
		lblNewLabel_1.setImage(imageNew);
		
		
		Button btnCheckButton = new Button(composite_1, SWT.CHECK);
		btnCheckButton.setBounds(139, 52, 121, 20);
		btnCheckButton.setText("Check Button");
		
		TabItem tbtmNewItem = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("New Item");
		
		Button btnNewButton = new Button(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(btnNewButton);
		btnNewButton.setText("New Button");
		
		TabItem tbtmNewItem_1 = new TabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("New Item");
		
		Composite composite_2 = new Composite(composite, SWT.BORDER | SWT.NO_MERGE_PAINTS | SWT.NO_REDRAW_RESIZE | SWT.NO_RADIO_GROUP | SWT.EMBEDDED);
		composite_2.setBounds(23, 197, 342, 132);
		
		ToolBar toolBar = new ToolBar(composite_2, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(10, 10, 224, 28);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("New Item");
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("New Item");
		
		Combo combo = new Combo(composite_2, SWT.NONE);
		combo.setBounds(240, 10, 92, 28);
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setAlwaysShowScrollBars(true);
		scrolledComposite.setBounds(415, 53, 133, 159);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setTouchEnabled(true);
		
		StyledText styledText = new StyledText(scrolledComposite, SWT.BORDER);
		scrolledComposite.setContent(styledText);
		scrolledComposite.setMinSize(styledText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item");

	}
}
