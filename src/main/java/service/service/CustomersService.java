package service.service;

import dataaccess.entity.Customers;
import dataaccess.repository.CustomersRepository;

import java.util.List;

public class CustomersService {

    private final CustomersRepository customersRepository = new CustomersRepository();

    public void insert(Customers customer) {
        customersRepository.insert(customer);
    }

    public void update(Customers customer, Integer id) {
        customersRepository.update(customer, id);
    }

    public void delete(Integer id) {
        customersRepository.delete(id);
    }

    public List<Customers> findAll() {
        return customersRepository.findAll();
    }

    public Customers findById(Integer id) {
        return customersRepository.findById(id);
    }
}
