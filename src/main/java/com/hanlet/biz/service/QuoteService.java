package com.hanlet.biz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hanlet.biz.entity.Quote;

public interface QuoteService extends BaseService<Quote, String>{

	public Page<Quote> findPage(Pageable pageable, Quote quote);

}
