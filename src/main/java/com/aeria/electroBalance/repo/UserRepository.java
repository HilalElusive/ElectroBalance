package com.aeria.electroBalance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.aeria.electroBalance.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserName(String userName);
	
	//@Query(value = "SELECT * FROM users u WHERE u.user_role = 'USER'", nativeQuery = true)
		List<User> findByRole(String role);

}
