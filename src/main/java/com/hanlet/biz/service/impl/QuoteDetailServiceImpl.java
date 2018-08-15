package com.hanlet.biz.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.QuoteDetailDao;
import com.hanlet.biz.entity.Product;
import com.hanlet.biz.entity.Quote;
import com.hanlet.biz.entity.QuoteDetail;
import com.hanlet.biz.service.ProductService;
import com.hanlet.biz.service.QuoteDetailService;
import com.hanlet.biz.service.QuoteService;
import com.hanlet.util.ExportExcelUtil;

@Service
public class QuoteDetailServiceImpl extends BaseServiceImpl<QuoteDetail, String> implements QuoteDetailService {

	private QuoteDetailDao quoteDetailDao;
	
	@Autowired
	public void setQuoteDetailDao(QuoteDetailDao quoteDetailDao) {
		this.quoteDetailDao = quoteDetailDao;
		this.setGenericDAO(quoteDetailDao);
	}

	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private ProductService productService;
	
	
	@Override
	public Page<QuoteDetail> findPage(Pageable pageable, QuoteDetail quoteDetail) {
		ExampleMatcher matcher = ExampleMatcher.matching().withNullHandler(NullHandler.IGNORE);
		Example<QuoteDetail> ex = Example.of(quoteDetail, matcher);
		return this.quoteDetailDao.findAll(ex, pageable);
	}

	@Override
	public Boolean saveQuoteDetail(Map<String, Object> params) {
		try {
			String quoteId = params.get("quoteId")+"";
			List<Map<String, Object>> products = (List<Map<String, Object>>) params.get("products");
			Quote q = quoteService.getById(quoteId);
			List<QuoteDetail> oldList = quoteDetailDao.findByQuoteId(quoteId);
			quoteDetailDao.delete(oldList);
			List<QuoteDetail> list = new ArrayList<QuoteDetail>();
			for (Map<String, Object> temp : products) {
				QuoteDetail qDetail = new QuoteDetail();
				qDetail.setCreateDateTime(new Date());
				qDetail.setUpdateDateTime(new Date());
				qDetail.setPrice(new BigDecimal(temp.get("productPrice")+""));
				qDetail.setReportPrice(new BigDecimal(temp.get("productReportPrice")+""));
				qDetail.setQuote(q);
				Product p = productService.getById(temp.get("productId")+"");
				qDetail.setProduct(p);
				qDetail.setProductCount(Integer.parseInt(temp.get("productCount")+""));
				list.add(qDetail);
			}
			quoteDetailDao.save(list);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<QuoteDetail> getByQuoteId(String quoteId) {
		return quoteDetailDao.findByQuoteId(quoteId);
	}

	@Override
	public void exportExcel(String quoteId, ServletOutputStream outputStream) {
		
		List<QuoteDetail> list = quoteDetailDao.findByQuoteId(quoteId);
		
		ExportExcelUtil.exportUserExcel(list, outputStream);
	}
	
}
