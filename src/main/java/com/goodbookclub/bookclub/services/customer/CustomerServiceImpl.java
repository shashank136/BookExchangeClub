package com.goodbookclub.bookclub.services.customer;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodbookclub.bookclub.domains.Customer;
import com.goodbookclub.bookclub.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public List<Customer> listOfCustomers() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		log.info("Total no. of customers: "+customers.size());
		return customers;
	}

	@Override
	public Customer getCustomerById(Integer id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer==null)
			log.error("Customer doesn't exist with id: "+id);
		else
			log.info("Customer found: "+ customer);
		return customer;
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		log.info("Customer saved/updated: "+customer);
		return customerRepository.save(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		if(customer==null)
			log.error("Customer doesn't exist");
		else {
			log.info("Customer deleted: "+customer);
			customerRepository.deleteById(id);
		}
	}

}
