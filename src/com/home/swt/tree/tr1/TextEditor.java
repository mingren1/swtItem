package com.home.swt.tree.tr1;

import java.io.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.jface.resource.*;
import org.eclipse.swt.custom.StyledText;

public class TextEditor {
	Display display = new Display();
	Shell shell = new Shell(display);
	StyledText styledText;
	boolean unsaved;
	File file;
	String lastDirectory;

	public TextEditor() {
		shell.setLayout(new GridLayout());
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		ToolItem item1 = new ToolItem(toolBar, SWT.PUSH);
		item1.setText("New");
		item1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (saveChanges()) {
					file = null;
					styledText.setText("");
				}
			}
		});
		ToolItem item2 = new ToolItem(toolBar, SWT.PUSH);
		item2.setText("Open");
		item2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				loadText();
			}
		});
		ToolItem item3 = new ToolItem(toolBar, SWT.PUSH);
		item3.setText("Save");
		item3.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				saveText();
			}
		});
		ToolItem item4 = new ToolItem(toolBar, SWT.PUSH);
		item4.setText("Copy");
		item4.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				styledText.copy();
			}
		});
		ToolItem item5 = new ToolItem(toolBar, SWT.PUSH);
		item5.setText("Cut");
		item5.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				styledText.cut();
			}
		});
		ToolItem item6 = new ToolItem(toolBar, SWT.PUSH);
		item6.setText("Paste");
		item6.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				styledText.paste();
			}
		});
		ToolItem item7 = new ToolItem(toolBar, SWT.PUSH);
		item7.setText("Exit");
		item7.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (saveChanges())
					shell.dispose();
			}
		});
		toolBar.pack();
		styledText = new StyledText(shell, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		styledText.setLayoutData(new GridData(GridData.FILL_BOTH));
		Font font = new Font(shell.getDisplay(), "Book Antiqua", 12, SWT.NORMAL);
		styledText.setFont(font);
		styledText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				unsaved = true;
			}
		});
		shell.setText("Editor");
		shell.setSize(400, 200);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	boolean saveChanges() {
		if (!unsaved)
			return true;
		MessageBox box = new MessageBox(shell, SWT.ICON_WARNING | SWT.YES | SWT.NO | SWT.CANCEL);
		box.setMessage("save changes? ");
		box.setText("Editor");
		int condition = box.open();
		if (condition == SWT.YES) {
			return saveText();
		} else if (condition == SWT.NO) {
			return true;
		} else {
			return false;
		}
	}

	boolean loadText() {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		if (lastDirectory != null)
			dialog.setFilterPath(lastDirectory);

		String selectedFile = dialog.open();
		if (selectedFile == null) {
			System.out.println("File is not opened");
			return false;
		}
		file = new File(selectedFile);
		lastDirectory = file.getParent();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
			styledText.setText(buffer.toString());
			return true;
		} catch (IOException e) {
		}
		return false;
	}

	boolean saveText() {
		if (file == null) {
			FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
			if (lastDirectory != null)
				fileDialog.setFilterPath(lastDirectory);

			String selectedFile = fileDialog.open();
			if (selectedFile == null) {
				System.out.println("File is not saved");
				return false;
			}
			file = new File(selectedFile);
			lastDirectory = file.getParent();
		}
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(styledText.getText());
			writer.close();
			unsaved = false;
			return true;
		} catch (IOException e) {
		}
		return false;
	}

	public static void main(String[] args) {
		new TextEditor();
	}
}