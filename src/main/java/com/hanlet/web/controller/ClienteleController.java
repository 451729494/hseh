package com.hanlet.web.controller;

import static org.mockito.Matchers.contains;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.hanlet.biz.service.ClienteleService;
import com.hanlet.util.Result;
import com.hanlet.util.ResultStatus;

@Controller
@RequestMapping(value="/clientele")
public class ClienteleController {

	@Autowired
	private ClienteleService clienteleService;
	
	@ResponseBody
	@PostMapping
	public Result<Clientele> saveClientele(@RequestBody Clientele clientele){
		Result<Clientele> result = new Result<Clientele>();
		try {
			clientele.setCreateDateTime(new Date());
			clientele.setUpdateDateTime(new Date());
			Clientele returnClientele = clienteleService.save(clientele);
			result.setData(returnClientele);
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
	public Result<Clientele> deleteCliente(@PathVariable(name="id",required=true) String clienteleId){
		Result<Clientele> result = new Result<Clientele>();
		try {
			clienteleService.deleteById(clienteleId);
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
	public Result<Clientele> getCliente(@PathVariable(name="id",required=true) String clienteleId){
		Result<Clientele> result = new Result<Clientele>();
		try {
			Clientele client = clienteleService.getById(clienteleId);
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
	public Result<Clientele> updateClientele(@RequestBody Clientele clientele){
		Result<Clientele> result = new Result<Clientele>();
		try {
			Clientele source = clienteleService.getById(clientele.getClienteleId());
			clientele.setCreateDateTime(source.getCreateDateTime());
			clientele.setUpdateDateTime(new Date());
			Clientele returnClientele = clienteleService.save(clientele);
			result.setData(returnClientele);
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
	public Result<Page<Clientele>> listClientele(@PathVariable(value = "page",required=true) 
	String page, @PathVariable(value = "size") String size, 
			@RequestBody Clientele clientele){
		Result<Page<Clientele>> result = new Result<Page<Clientele>>();
		try {
			Pageable pageable = new PageRequest(Integer.parseInt(page)-1, Integer.parseInt(size), 
					Sort.Direction.DESC,"createDateTime");
			Page<Clientele> pageContent = clienteleService.findPage(pageable,clientele);
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
	public Result<List<Map<String, String>>> forselectClientele(){
		Result<List<Map<String, String>>> result = new Result<List<Map<String, String>>>();
		try {
			List<Map<String, String>> list = clienteleService.findForSelect();
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
