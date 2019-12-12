package com.home.swt.tableview.item1;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
*
* ��tableViewer�ṩ������(IStructuredContentPorvider)�ͱ�ǩ��(ITableLabelProvider)
* �����һ��tableviewer��ʾ���ݣ��Ǳ�������ṩ�������ͱ�ǩ���� �������������Ǵ�List(Ҳ�����������ļ����ࣩ
* ����ȡ��һ������(����People��Ӧ�ű���һ�У� ���ݿ��һ����¼),��ǩ���������Ǵ�һ����������ȡ��һ���ֶΣ��������䣬��Ӧ�ű���е�һ����Ԫ��
* �����ݿ���ĳһ�е�һ��ֵ)
*
*
* ������
*/
public class ContextProvider implements IStructuredContentProvider{


	/**
	 *
	 * ����������м��м�¼���д���
	 */
	@Override
	public Object[] getElements(Object element) {
		// �����ｫlistת��������
		if (element instanceof List) {
			/**
			 * nstanceof��Java��һ����Ԫ����������==��>�� <��ͬһ�ණ����������������ĸ��ɵģ� ����Ҳ��Java�ı����ؼ��֡�
			 * ���������ǲ�������ߵĶ����Ƿ������ұߵ����ʵ��
			 */
			return ((List) element).toArray();// ��listת����һ������
		} else {
			// ����һ����
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
