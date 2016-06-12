package com.relishcode.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relishcode.model.Customer;
import com.relishcode.persistence.dao.CustomerDao;
import com.relishcode.services.CustomerService;

@Stateless
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerDao customerDao;
	
	
	@Override
	public Customer createCustomer(Customer customer) {
		customerDao.create(customer);
		return customer;
	}
	
	@Override
	public void deleteCustomer(long id) {
		customerDao.delete(id);		
	}
	
	@Override
	public Customer getCustomer(long id) {
		return customerDao.read(id);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerDao.update(customer);		
	}
	
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
