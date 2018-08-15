package com.hanlet.web.controller;

import java.util.Date;

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

import com.hanlet.biz.entity.Clientele;
import com.hanlet.biz.entity.Quote;
import com.hanlet.biz.service.ClienteleService;
import com.hanlet.biz.service.QuoteService;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

@Controller
@RequestMapping(value="/quote")
public class QuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private ClienteleService clienteleService;
	
	@ResponseBody
	@PostMapping
	public Result<Quote> saveQuote(@RequestBody Quote quote){
		Result<Quote> result = new Result<Quote>();
		try {
			Clientele c = clienteleService.getById(quote.getClientele().getClienteleId());
			quote.setClientele(c);
			quote.setCreateDateTime(new Date());
			quote.setUpdateDateTime(new Date());
			Quote returnQuote = quoteService.save(quote);
			result.setData(returnQuote);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public Result<Quote> deleteCliente(@PathVariable(name="id",required=true) String quoteId){
		Result<Quote> result = new Result<Quote>();
		try {
			quoteService.deleteById(quoteId);
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
	public Result<Quote> getCliente(@PathVariable(name="id",required=true) String quoteId){
		Result<Quote> result = new Result<Quote>();
		try {
			Quote client = quoteService.getById(quoteId);
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
	@PutMapping
	public Result<Quote> updateQuote(@RequestBody Quote quote){
		Result<Quote> result = new Result<Quote>();
		try {
			Quote old = quoteService.getById(quote.getQuoteId());
			quote.setCreateDateTime(old.getCreateDateTime());
			quote.setUpdateDateTime(new Date());
			Quote returnQuote = quoteService.save(quote);
			result.setData(returnQuote);
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
	public Result<Page<Quote>> listQuote(@PathVariable(value = "page",required=true) 
	String page, @PathVariable(value = "size") String size, 
			@RequestBody Quote quote){
		Result<Page<Quote>> result = new Result<Page<Quote>>();
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size), 
					Sort.Direction.DESC,"createDateTime");
			Page<Quote> pageContent = quoteService.findPage(pageable,quote);
			result.setData(pageContent);
			return result;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
		
	}
}
