package com.evan.jta.service;

import com.evan.jta.model.ds1.User;
import com.evan.jta.model.ds2.Product;
import com.evan.jta.repository.ds1.UserRepository;
import com.evan.jta.repository.ds2.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author EvanHuang
 * @date 1/10/2019 6:34 PM
 * @since
 */
@Service
public class JtaService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void jtaTesting() {
        User user = new User();
        userRepository.save(user);

        Product product = new Product();
        productRepository.save(product);
//        int i = 1 / 0;
    }
}
