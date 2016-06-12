package com.relishcode.persistence.dao;

import java.util.List;

public interface GenericDao<E, PK> {

	public E create(E entity);
	public E read(PK key);
	public E update(E entity);
	public void delete(PK key);
	public List<E> findAll();
}
