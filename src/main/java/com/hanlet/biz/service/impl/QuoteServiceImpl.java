package com.hanlet.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.QuoteDao;
import com.hanlet.biz.entity.Quote;
import com.hanlet.biz.service.QuoteService;

@Service
public class QuoteServiceImpl extends BaseServiceImpl<Quote, String> implements QuoteService{

	private QuoteDao quoteDao;

	@Autowired
	public void setQuoteDao(QuoteDao quoteDao) {
		this.quoteDao = quoteDao;
		this.setGenericDAO(quoteDao);
	}

	@Override
	public Page<Quote> findPage(Pageable pageable, Quote quote) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("quoteName", GenericPropertyMatchers.contains())
				.withMatcher("createUser", GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact()).withNullHandler(NullHandler.IGNORE);
		Example<Quote> ex = Example.of(quote, matcher);
		return quoteDao.findAll(ex, pageable);
	}
	
	
}
