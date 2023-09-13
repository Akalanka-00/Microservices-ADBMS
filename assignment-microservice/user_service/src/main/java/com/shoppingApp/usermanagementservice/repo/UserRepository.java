package com.shoppingApp.usermanagementservice.repo;

import com.shoppingApp.usermanagementservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    User findByUsername(String emailId);
//    User findByEmailIdAndPassword(String emailId,String password);

    User findByEmailId(String emailId);

}
