package com.home.swt.tree.tr1;

import java.io.File;
 
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
 
public class WsdlTree {
	private Image wsdlImage;
	private Image directoryImage;
	private String directory;//文件目录
	
	public void listFiles(File rootFile, TreeItem item) {
		File[] files = rootFile.listFiles();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				TreeItem subItem = new TreeItem(item, SWT.None);
				if(directoryImage!=null&&wsdlImage!=null){
					if(files[i].isDirectory()){
						subItem.setImage(directoryImage);
					}else{
						subItem.setImage(wsdlImage);
					}
				}
				subItem.setText(files[i].getName());
				listFiles(files[i], subItem);
			}
		}
	}
 
	public Tree createTree(Composite parent) {
			Tree tree = new Tree(parent, SWT.BORDER | SWT.FULL_SELECTION);
			File file = new File(directory);
			File[] files = file.listFiles();
			tree.setItemCount(0);
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if (null != fileName) {
					if (!"".equals(fileName)) {
						String lowerFileName = fileName.toLowerCase();
						if (lowerFileName.endsWith(".wsdl")||"WSDL".equals(fileName)) {
							TreeItem item = new TreeItem(tree, SWT.NONE);
							if(files[i].isDirectory()){
								item.setImage(directoryImage);
							}else{
								item.setImage(wsdlImage);
							}
							item.setText(files[i].getName());
							// 调用递归方法
							listFiles(files[i], item);
						}
					}
				}
			}
			return tree;
	}
	public static void main(String[] args) {
		WsdlTree wt=new WsdlTree();
		String dir = "C:/Users/42194/Desktop/1";
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setSize(500, 200);
		wt.setDirectory(dir);
		{
			wt.createTree(shell);
		}
		shell.open();
 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
 
	public Image getWsdlImage() {
		return wsdlImage;
	}
 
	public void setWsdlImage(Image wsdlImage) {
		this.wsdlImage = wsdlImage;
	}
 
	public Image getDirectoryImage() {
		return directoryImage;
	}
 
	public void setDirectoryImage(Image directoryImage) {
		this.directoryImage = directoryImage;
	}
 
	public String getDirectory() {
		return directory;
	}
 
	public void setDirectory(String directory) {
		this.directory = directory;
	}
 
	public WsdlTree(Image wsdlImage, Image directoryImage, String directory) {
		super();
		this.wsdlImage = wsdlImage;
		this.directoryImage = directoryImage;
		this.directory = directory;
	}
 
	public WsdlTree() {
		super();
	}
 
	public WsdlTree(String directory) {
		super();
		this.directory = directory;
	}
	
	
}