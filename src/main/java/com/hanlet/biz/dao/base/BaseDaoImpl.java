package com.hanlet.biz.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseDao<T, ID> {

	private final EntityManager em;

	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
	}

	@Override
	public Long queryCountBySql(String sql, Map<String, String> params) {
		Query query = em.createQuery(sql);
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		List<?> list = query.getResultList();
		Long count = (Long) list.get(0);
		return count;
	}

	@Override
	public List<T> queryListByHql(String hql, Map<String, String> params) {
		Query query = em.createQuery(hql);
		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		List<T> list = query.getResultList();
		return list;
	}

}
