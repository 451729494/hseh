package com.hanlet.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.ProductDao;
import com.hanlet.biz.entity.Product;
import com.hanlet.biz.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, String> implements ProductService {

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
		this.setGenericDAO(productDao);
	}

	@Override
	public Page<Product> findPage(Pageable pageable, Product product) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("productName", GenericPropertyMatchers.contains())
				.withMatcher("manufactor", GenericPropertyMatchers.contains())
				.withMatcher("creator", GenericPropertyMatchers.exact()).withNullHandler(NullHandler.IGNORE);
		Example<Product> ex = Example.of(product, matcher);
		return productDao.findAll(ex, pageable);
	}

	@Override
	public List<Map<String, String>> findForSelect() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Product> products = productDao.findAll();
		for(Product p : products){
			Map<String, String> map = new HashMap<String, String>();
			map.put("productId", p.getProductId());
			map.put("productName", p.getProductName());
			list.add(map);
			
		}
		return list;
	}
	
	
	

}
