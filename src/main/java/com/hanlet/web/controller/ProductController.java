package com.hanlet.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
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

import com.hanlet.biz.entity.Product;
import com.hanlet.biz.service.ProductService;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

@Controller
@RequestMapping(value="/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@ResponseBody
	@PostMapping
	public Result<Product> saveProduct(@RequestBody Product product){
		Result<Product> result = new Result<Product>();
		try {
			product.setCreateDateTime(new Date());
			product.setUpdateDateTime(new Date());
			Product returnProduct = productService.save(product);
			result.setData(returnProduct);
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
	public Result<Product> deleteCliente(@PathVariable(name="id",required=true) String productId){
		Result<Product> result = new Result<Product>();
		try {
			productService.deleteById(productId);
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
	public Result<Product> getCliente(@PathVariable(name="id",required=true) String productId){
		Result<Product> result = new Result<Product>();
		try {
			Product client = productService.getById(productId);
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
	public Result<Product> updateProduct(@RequestBody Product product){
		Result<Product> result = new Result<Product>();
		try {
			Product source = productService.getById(product.getProductId());
			product.setCreateDateTime(source.getCreateDateTime());
			product.setUpdateDateTime(new Date());
			Product returnProduct = productService.save(product);
			result.setData(returnProduct);
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
	public Result<Page<Product>> listProduct(@PathVariable(value = "page",required=true) 
	String page, @PathVariable(value = "size") String size, 
			@RequestBody Product product){
		Result<Page<Product>> result = new Result<Page<Product>>();
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size), 
					Sort.Direction.DESC,"createDateTime");
			Page<Product> pageContent = productService.findPage(pageable,product);
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
	@GetMapping(value="/forselect")
	public Result<List<Map<String, String>>> forselectProduct(){
		Result<List<Map<String, String>>> result = new Result<List<Map<String, String>>>();
		try {
			List<Map<String, String>> list = productService.findForSelect();
			result.setData(list);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setStatus(ResultStatus.ERROR.getStatus());
			result.setDesc(ResultStatus.ERROR.getDesc());
			return result;
		}
	}
}
