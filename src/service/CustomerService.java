package service;

import model.Customer;

import java.util.*;

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
        List<Customer> allCusts = new ArrayList<Customer>();

        for (Map.Entry<String, Customer> entry : allCustomers.entrySet()) {
            allCusts.add(entry.getValue());
        }

        return allCusts;
    }
}
