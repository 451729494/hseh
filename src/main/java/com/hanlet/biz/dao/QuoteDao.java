package com.hanlet.biz.dao;

import org.springframework.stereotype.Repository;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.entity.Quote;

@Repository
public interface QuoteDao extends BaseDao<Quote, String> {

}
