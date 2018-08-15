package com.hanlet.biz.service;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BaseService <T, ID extends Serializable>{
	/**
	 * 根据主键获取实体。
	 * 
	 * @param id
	 * @return
	 */
	public T getById(ID id);

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity);

	/**
	 * 保存迭代器里的实体
	 * 
	 * @param entities
	 * @return
	 */
	public <S extends T> Iterable<S> save(Iterable<S> entities);

	/**
	 * 判断实体是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean exists(ID id);

	/**
	 * 得到所有的实体
	 * 
	 * @return
	 */
	public Iterable<T> findAll();

	/**
	 * 删除指定的实体
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * 根据主键删除指定实体
	 * 
	 * @param id
	 */
	public void deleteById(ID id);

	/**
	 * 删除集合中的全部实体
	 * 
	 * @param entities
	 */
	public void deleteAll(Iterable<T> entities);

	/**
	 * 得到排序后的集合
	 * 
	 * @param sort
	 * @return
	 */
	public Iterable<T> findAll(Sort sort);

	/**
	 * 得到分页
	 * 
	 * @param pageable
	 * @return
	 */
	public Page<T> findAll(Pageable pageable);
	
	
}
