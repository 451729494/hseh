package com.hanlet.biz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hanlet.biz.entity.Product;

public interface ProductService extends BaseService<Product, String>{

	public Page<Product> findPage(Pageable pageable, Product product);

	public List<Map<String, String>> findForSelect();

}
