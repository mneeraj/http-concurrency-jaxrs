package com.relishcode.resources;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.relishcode.model.Customer;
import com.relishcode.services.CustomerService;

@Path("/customers")
public class CustomerResource {
	private static final String CUSTOMER_URI = "/customers";
	@Inject
	CustomerService customerService;
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer customer) {
		Customer c = customerService.createCustomer(customer);
		return Response.created(URI.create(CUSTOMER_URI + "/" + c.getId())).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id") long id) {
		Customer c = customerService.getCustomer(id);
		if (c != null) {
			return Response.ok(c).lastModified(c.getUpdateTime()).build();			
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomers() {
		List<Customer> customers = customerService.findAll();
		if(customers != null) {
			return Response.ok(customers).build();
		}
		else {
			return Response.noContent().build();
		}
		
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@PathParam("id") long id, Customer customer, @Context Request request) {
		Customer c = customerService.getCustomer(id);
		if (c != null) {
			
			ResponseBuilder responseBuilder = request.evaluatePreconditions(c.getUpdateTime());
			if (responseBuilder == null) {
				customer.setId(id);
				Customer updatedCustomer = customerService.updateCustomer(customer);
				return Response.ok(updatedCustomer).lastModified(updatedCustomer.getUpdateTime()).build();
			}
			else {
				return responseBuilder.build();
			}
		}
		else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
