package com.home.swt.layout.item.model;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class PersonContextProvider implements IStructuredContentProvider{
	
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
