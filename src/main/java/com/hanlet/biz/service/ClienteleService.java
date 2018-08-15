package com.hanlet.biz.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hanlet.biz.entity.Clientele;

public interface ClienteleService extends BaseService<Clientele, String>{

	Page<Clientele> findPage(Pageable pageable, Clientele clientele);

	List<Map<String, String>> findForSelect();

}
