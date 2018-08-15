package com.hanlet.biz.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {
	/**
	 * 通过sql查询数量
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long queryCountBySql(String sql, Map<String, String> params);
	
	/**
	 * 通过sql查询记录S
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> queryListByHql(String hql, Map<String, String> params);
}