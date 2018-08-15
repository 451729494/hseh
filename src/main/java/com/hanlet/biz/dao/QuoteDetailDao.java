package com.hanlet.biz.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.entity.QuoteDetail;

@Repository
public interface QuoteDetailDao extends BaseDao<QuoteDetail, String>  {

	@Query("select u from QuoteDetail u where u.quote.quoteId = :quoteId")
	List<QuoteDetail> findByQuoteId(@Param(value = "quoteId")String quoteId);

}
