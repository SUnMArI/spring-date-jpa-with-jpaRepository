package com.example.exercies3.service.serviceImplement;

import com.example.exercies3.model.dto.request.CustomerRequest;
import com.example.exercies3.model.dto.response.CustomerResponse;
import com.example.exercies3.model.entity.Customer;
import com.example.exercies3.model.entity.Email;
import com.example.exercies3.repository.CustomerRepository;
import com.example.exercies3.repository.EmailRepository;
import com.example.exercies3.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final EmailRepository emailRepository;

    @Override
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Email email = emailRepository.save(new Email(null,customerRequest.getEmail()));
        Customer customer = customerRequest.toCustomer(email);
        customerRepository.save(customer);
        return customerRepository.save(customer).toResponse();
    }

    @Override
    public boolean deleteCustomerById(Long id) {
        System.err.println("Hello");
        Optional<Customer> customer = customerRepository.findById(id);
        System.out.println("Hello: "+customer.toString());
        if(customer.isPresent()) {
            Customer customerToDelete = customer.get();
            customerRepository.deleteById(id);
            emailRepository.deleteById( customerToDelete.getEmail().getId());
            return true;
        }else throw new RuntimeException("Customer not found");
    }

    @Override
    public CustomerResponse updateCustomerById(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            Customer customerToUpdate = customer.get();
            Email email = customerToUpdate.getEmail();
            email.setEmail(customerRequest.getEmail());
            emailRepository.save(email);
            customerToUpdate.updateCustomer(customerRequest);
            return customerRepository.save(customerToUpdate).toResponse();
        }else throw new RuntimeException("Customer not found");
    }

    @Override
    public List<CustomerResponse> findAllCustomer(int pageNo, int pageSize, String sortBy, String sortDirection) {
       Customer customer = new Customer();
       Sort sort = null;
       if(sortDirection.equalsIgnoreCase("asc")) {
           sort = Sort.by(Sort.Direction.ASC, sortBy);
       } else if (sortDirection.equalsIgnoreCase("desc")) {
           sort = Sort.by(Sort.Direction.DESC, sortBy);
       }
      List<Customer> customerList =  customerRepository.findAll(PageRequest.of(pageNo-1,pageSize, sort)).getContent();
        return customerList.stream().map(customer::toResponse).toList();

    }

    @Override
    public CustomerResponse findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()) {
            return customer.get().toResponse();
        }else throw new RuntimeException("Customer not found");
    }
}
