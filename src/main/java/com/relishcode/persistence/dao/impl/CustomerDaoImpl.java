package com.relishcode.persistence.dao.impl;

import com.relishcode.model.Customer;
import com.relishcode.persistence.dao.CustomerDao;

public class CustomerDaoImpl extends GenericDaoImpl<Customer, Long> implements CustomerDao {
	public CustomerDaoImpl() {
		super(Customer.class);
	}
}
