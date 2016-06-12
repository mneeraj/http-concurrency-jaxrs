package com.relishcode.persistence.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.relishcode.persistence.dao.GenericDao;

public abstract class GenericDaoImpl<E, PK> implements GenericDao<E, PK> {
	@PersistenceContext(unitName = "CustomerPU")
	private EntityManager entityManager;
	
	private Class<E> entityClass;
	
	public GenericDaoImpl(Class<E> clazz) {
		entityClass = clazz;
	}
		
	@Override
	public E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	public void delete(PK key) {
		E entity = entityManager.find(entityClass, key);
		if (entity != null) {
			entityManager.remove(entity);
		}
	}
	
	@Override
	public E read(PK key) {
		return entityManager.find(entityClass, key);
	}
		
	@Override
	public E update(E entity) {
		return entityManager.merge(entity);
	}
	
	@Override
	public List<E> findAll() {		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteria = criteriaBuilder.createQuery(entityClass);
		criteria.select(criteria.from(entityClass));
		TypedQuery<E> query = entityManager.createQuery(criteria);		
		return query.getResultList();	
	}

}
