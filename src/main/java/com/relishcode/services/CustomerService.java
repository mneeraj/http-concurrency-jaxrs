package com.relishcode.services;

import java.util.List;

import com.relishcode.model.Customer;

public interface CustomerService {
	
	public Customer createCustomer(Customer customer);
	public void deleteCustomer(long id);
	public Customer getCustomer(long id);
	public Customer updateCustomer(Customer customer);
	public List<Customer> findAll();
}
