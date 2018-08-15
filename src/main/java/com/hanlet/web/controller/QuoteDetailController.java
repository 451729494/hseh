package com.hanlet.web.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanlet.biz.entity.Quote;
import com.hanlet.biz.entity.QuoteDetail;
import com.hanlet.biz.service.QuoteDetailService;
import com.hanlet.biz.service.QuoteService;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

@Controller
@RequestMapping(value="/quoteDetail")
public class QuoteDetailController {

	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuoteDetailService quoteDetailService;
	
	@ResponseBody
	@PostMapping
	public Result<Boolean> saveQuoteDetail(@RequestBody Map<String, Object> params){
		Result<Boolean> result = new Result<Boolean>();
		try {
			boolean saveFlag = quoteDetailService.saveQuoteDetail(params);
			result.setData(saveFlag);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			result.setData(false);
			return result;
		}
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public Result<QuoteDetail> deleteQuoteDetail(@PathVariable(name="id",required=true) String quoteDetailId){
		Result<QuoteDetail> result = new Result<QuoteDetail>();
		try {
			quoteDetailService.deleteById(quoteDetailId);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@GetMapping(value="/{id}")
	public Result<QuoteDetail> getQuoteDetail(@PathVariable(name="id",required=true) String quoteDetailId){
		Result<QuoteDetail> result = new Result<QuoteDetail>();
		try {
			QuoteDetail client = quoteDetailService.getById(quoteDetailId);
			result.setData(client);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@GetMapping(value="/quote/{id}")
	public Result<List<QuoteDetail>> getQuoteDetailList(@PathVariable(name="id",required=true) String quoteId){
		Result<List<QuoteDetail>> result = new Result<List<QuoteDetail>>();
		try {
			List<QuoteDetail> quoteDetails = quoteDetailService.getByQuoteId(quoteId);
			result.setData(quoteDetails);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	
	@ResponseBody
	@PutMapping
	public Result<QuoteDetail> updateQuoteDetail(@RequestBody QuoteDetail quoteDetail){
		Result<QuoteDetail> result = new Result<QuoteDetail>();
		try {
			quoteDetail.setUpdateDateTime(new Date());
			QuoteDetail returnQuoteDetail = quoteDetailService.save(quoteDetail);
			result.setData(returnQuoteDetail);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@PostMapping(value="/list/{page}/{size}")
	public Result<Page<QuoteDetail>> listQuoteDetail(@PathVariable(value = "page",required=true) 
	String page, @PathVariable(value = "size") String size, 
			@RequestBody QuoteDetail quoteDetail){
		Result<Page<QuoteDetail>> result = new Result<Page<QuoteDetail>>();
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size), 
					Sort.Direction.DESC,"createDateTime");
			Page<QuoteDetail> pageContent = quoteDetailService.findPage(pageable,quoteDetail);
			result.setData(pageContent);
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
		
	}
	
	@ResponseBody
	@GetMapping(value="/quote/export/{id}")
	public void exportQuoteDetailList(@PathVariable(name="id",required=true) String quoteId, HttpServletResponse response){
		try {
			Quote quote = quoteService.getById(quoteId);
		 	response.setHeader("Content-Disposition", "attachment;filename=\"" +  URLEncoder.encode("报价单-"+ quote.getQuoteName()+".xls","UTF-8") + "\"");
            response.setContentType("application/vnd.ms-excel");
            quoteDetailService.exportExcel(quoteId, response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
