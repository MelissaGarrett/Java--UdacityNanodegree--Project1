package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService = new CustomerService(); // a Singleton

    private CustomerService() {} // 'private' so can't be instantiated

    public static CustomerService getInstance() { return customerService; }

    Map<String, Customer> allCustomers = new HashMap<String, Customer>();

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        allCustomers.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        Customer customer = allCustomers.get(customerEmail);

        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return (Collection<Customer>) allCustomers;
    }
}
