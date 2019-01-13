package com.evan.jta.repository.ds2;

import com.evan.jta.model.ds2.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author EvanHuang
 * @date 1/10/2019 6:31 PM
 * @since
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
