package com.home.swt.tree.tr1;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.TreeItem;

/** 带有半选状态的监听 
 * @author hKF76182 zero 
 * 
 */  
 public class TreeSelectListener implements SelectionListener{  
    @Override  
    public void widgetSelected(SelectionEvent e) {  
        if(!(e.item instanceof TreeItem)){  
            return;  
        }  
        TreeItem treeItem=(TreeItem) e.item;  
        downLookup(treeItem);  
        upLookup(treeItem);  
    }  

    @Override  
    public void widgetDefaultSelected(SelectionEvent e) {  
        // TODO Auto-generated method stub  
          
    }  
      
    private void downLookup(TreeItem treeItem){  
        boolean checked=treeItem.getChecked();  
        TreeItem[] children = treeItem.getItems();  
        for (TreeItem item : children) {  
            item.setChecked(checked);  
            downLookup(item);  
        }  
        if(checked){  
            treeItem.setGrayed(false);  
        }  
    }  

    private void upLookup(TreeItem treeItem) {  
        TreeItem pitem = treeItem.getParentItem();  
        if (pitem != null) {  
            TreeItem[] childrens = pitem.getItems();  
            boolean hasChildrenChecked = false;  
            boolean hasChildrenNoChecked = false;  
            for (TreeItem children : childrens) {  
                if (children.getChecked()) {  
                    hasChildrenChecked = true;  
                    if (children.getGrayed()) {  
                        hasChildrenNoChecked = true;  
                        break;  
                    }  
                } else {  
                    hasChildrenNoChecked = true;  
                    if(hasChildrenChecked){  
                        break;  
                    }  
                }  
            }  
            pitem.setChecked(hasChildrenChecked);  
            pitem.setGrayed(hasChildrenNoChecked);  
            upLookup(pitem);  
        }  
    }  
}  
