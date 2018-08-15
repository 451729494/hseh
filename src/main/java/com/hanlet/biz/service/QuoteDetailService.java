package com.hanlet.biz.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hanlet.biz.entity.QuoteDetail;

public interface QuoteDetailService extends BaseService<QuoteDetail, String>{

	Page<QuoteDetail> findPage(Pageable pageable, QuoteDetail quoteDetail);

	Boolean saveQuoteDetail(Map<String, Object> params);

	List<QuoteDetail> getByQuoteId(String quoteId);

	void exportExcel(String quoteId, ServletOutputStream outputStream);

}
