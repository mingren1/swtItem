package com.home.swt.tableview.item1;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
*
* 给tableViewer提供内容器(IStructuredContentPorvider)和标签器(ITableLabelProvider)
* 如果想一个tableviewer显示数据，那必须给它提供内容器和标签器， 内容器的作用是从List(也可以是其他的集合类）
* 中提取出一个对象(例如People对应着表格的一行， 数据库的一条记录),标签器的作用是从一个对象中提取出一个字段（例如年龄，对应着表格中的一个单元格
* ，数据库中某一列的一个值)
*
*
* 内容器
*/
public class ContextProvider implements IStructuredContentProvider{


	/**
	 *
	 * 在这里对所有集中记录进行处理
	 */
	@Override
	public Object[] getElements(Object element) {
		// 在这里将list转换成数组
		if (element instanceof List) {
			/**
			 * nstanceof是Java的一个二元操作符，和==，>， <是同一类东东。由于它是由字母组成的， 所以也是Java的保留关键字。
			 * 它的作用是测试它左边的对象是否是它右边的类的实例
			 */
			return ((List) element).toArray();// 将list转换成一个数组
		} else {
			// 返回一个空
			return new Object[0];
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub
	}


}
