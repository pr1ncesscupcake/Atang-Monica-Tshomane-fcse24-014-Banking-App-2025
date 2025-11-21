package service;

import dao.CustomerDAO;
import model.Customer;

import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public void addCustomer(Customer customer) {
        customerDAO.addCustomerReturnSuccess(customer);
    }

    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }
}
