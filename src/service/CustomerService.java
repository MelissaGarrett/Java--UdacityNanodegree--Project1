package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService = new CustomerService(); // a Singleton

    private CustomerService() {} // 'private' so can't be instantiated

    public static CustomerService getInstance() { return customerService; }

    Customer customer;
    Map<String, Customer> customers = new HashMap<String, Customer>();

    public void addCustomer(String email, String firstName, String lastName) {
        customer = new Customer(firstName, lastName, email);
        customers.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return (Collection<Customer>) customers;
    }
}
