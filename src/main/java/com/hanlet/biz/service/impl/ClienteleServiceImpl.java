package com.hanlet.biz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.NullHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hanlet.biz.dao.ClienteleDao;
import com.hanlet.biz.entity.Clientele;
import com.hanlet.biz.service.ClienteleService;

@Service
public class ClienteleServiceImpl extends BaseServiceImpl<Clientele, String> implements ClienteleService {

	
	private ClienteleDao clienteleDao;

	@Autowired
	public void setClienteleDao(ClienteleDao clienteleDao) {
		this.clienteleDao = clienteleDao;
		this.setGenericDAO(clienteleDao);
	}

	@Override
	public Page<Clientele> findPage(Pageable pageable, Clientele clientele) {
		ExampleMatcher matcher = ExampleMatcher.matching()
					.withMatcher("clienteleName", GenericPropertyMatchers.contains())
					.withMatcher("contactor", GenericPropertyMatchers.contains()).withNullHandler(NullHandler.IGNORE);
		
		Example<Clientele> ex = Example.of(clientele, matcher);
		return clienteleDao.findAll(ex, pageable);
	}

	@Override
	public List<Map<String, String>> findForSelect() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Clientele> clienteles = clienteleDao.findAll();
		for(Clientele c : clienteles){
			Map<String, String> map = new HashMap<String, String>();
			map.put("clienteleId", c.getClienteleId());
			map.put("clienteleName", c.getClienteleName());
			list.add(map);
		}
		return list;
	}
	
	

}
