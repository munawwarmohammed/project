package com.example.project.Controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.Repository.CustomerRepository;
import com.example.project.model.Customer;
@CrossOrigin
@RestController
@RequestMapping("api/customer")
public class CustomerControler {
    @Autowired
    private CustomerRepository customerRepository;



    @PostMapping("/")
    public Customer addcustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
    @GetMapping("/")
    public List<Customer> getAll(){
        return customerRepository.findAll();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) throws RelationNotFoundException
    {
        Customer cus = customerRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        cus.setFirst_name(customer.getFirst_name());
        cus.setLast_name(customer.getLast_name());
        cus.setPhone_number(customer.getPhone_number());
        cus.setCity(customer.getCity());
        cus.setFlowers(customer.getFlowers());
        cus.setGift(customer.getGift());
        Customer cust = customerRepository.save(cus);
        return ResponseEntity.ok(cust);
    }

    //get staff by id
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getStaffById(@PathVariable long id) throws RelationNotFoundException
    {
        Customer cus = customerRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        return ResponseEntity.ok(cus);
    }

    //delete staff
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteStaff(@PathVariable long id) throws RelationNotFoundException
    {
        Customer stf = customerRepository.findById(id)
        .orElseThrow(() -> new RelationNotFoundException("Invalid Id"));
        customerRepository.delete(stf);

        Map<String,Boolean> response = new HashMap<>();
        response.put("Deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   private static final long serialVersionUID=1L;
    public ResourceNotFoundException(String msg)
   {
       super(msg);
   } 
}
}

    



