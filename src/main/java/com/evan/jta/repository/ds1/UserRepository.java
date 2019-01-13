package com.evan.jta.repository.ds1;


import com.evan.jta.model.ds1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author EvanHuang
 * @date 1/10/2019 6:31 PM
 * @since
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
