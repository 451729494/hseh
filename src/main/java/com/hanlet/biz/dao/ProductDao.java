package com.hanlet.biz.dao;

import org.springframework.stereotype.Repository;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.entity.Product;

@Repository
public interface ProductDao extends BaseDao<Product, String> {

}
