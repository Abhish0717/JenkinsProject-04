package com.sunilos.p4.testmodel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sunilos.p4.bean.ProductBean;
import com.sunilos.p4.model.ProductModel;
import com.sunilos.p4.util.DataUtility;

public class TestProductModel {

	public static void main(String[] args) {

//		testAdd();
		testSearch();

	}

	private static void testSearch() {

		List<ProductBean> list = new ArrayList<ProductBean>();

		ProductBean bean = new ProductBean();

		bean.setProductName("abc");
		
		ProductModel model = new ProductModel();

		list = model.search(bean, 1, 10);

		Iterator<ProductBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getProductName());

		}

	}

	private static void testAdd() {

		ProductModel model = new ProductModel();

		ProductBean bean = new ProductBean();

		bean.setProductName("abc");
		bean.setProductCategory("abc");
		bean.setOrderDate(DataUtility.getDate("02/02/2002"));
		bean.setPrice(500);
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
