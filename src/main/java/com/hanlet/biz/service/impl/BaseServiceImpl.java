package com.hanlet.biz.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.hanlet.biz.dao.base.BaseDao;
import com.hanlet.biz.service.BaseService;

public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

	private BaseDao<T, ID> genericDAO;

	@Autowired
	public void setGenericDAO(BaseDao<T, ID> genericDAO) {
		this.genericDAO = genericDAO;
	}

	@Override
	public T getById(ID id) {
		return this.genericDAO.findOne(id);
	}

	@Override
	public T save(T entity) {
		this.genericDAO.save(entity);
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		this.genericDAO.save(entities);
		return entities;
	}

	@Override
	public boolean exists(ID id) {

		return this.genericDAO.exists(id);
	}

	@Override
	public Iterable<T> findAll() {

		return this.genericDAO.findAll();
	}

	@Override
	public void delete(T entity) {
		this.genericDAO.delete(entity);

	}

	@Override
	public void deleteById(ID id) {
		this.genericDAO.delete(id);

	}

	@Override
	public void deleteAll(Iterable<T> entities) {
		this.deleteAll(entities);

	}

	@Override
	public Iterable<T> findAll(Sort sort) {

		return this.genericDAO.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {

		return this.genericDAO.findAll(pageable);
	}
}
