package com.myshop.site.customer;

import com.myshop.common.entity.Customer;
import com.myshop.common.entity.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer findByEmail(String email ) {
        return customerRepository.findByEmail(email);
    }

    public List<Customer> listAll() {
        return (List<Customer>) customerRepository.findAll();
    }

    public boolean checkUnique(Integer id,String email) {
        Customer customer = customerRepository.findByEmail(email);
        if(customer==null) return true;

        if(id==null) {
            return false;
        }else {
            if(customer.getId()!=id) {
                return false;
            }
        }


        return true;
    }
    public Customer save(Customer customer) {
        customer.setEnabled(false);
        encodePassword(customer);

        String randomVerificationCode = RandomString.make(64);

        customer.setVerificationCode(randomVerificationCode);

        return customerRepository.save(customer);
    }

    private void encodePassword(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
    }
    public boolean enable(String verificationCode) {
        Customer customer = customerRepository.findByVerificationCode(verificationCode);
        if(customer == null || customer.isEnabled()) {
            return false;
        }else {

            customerRepository.updateEnabled(customer.getId());
            return true;
        }
    }

    public Customer saveAccount(Customer customer) {
        Customer oldUser = customerRepository.findById(customer.getId()).get();
        if(customer.getPassword().isEmpty()) {
            customer.setPassword(oldUser.getPassword());
        }else {
            encodePassword(customer);
        }


        customer.setEnabled(true);
        customer.setCreatedAt(oldUser.getCreatedAt());
        return customerRepository.save(customer);
    }
}
