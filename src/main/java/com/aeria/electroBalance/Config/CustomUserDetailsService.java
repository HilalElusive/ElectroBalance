package com.aeria.electroBalance.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aeria.electroBalance.entity.User;
import com.aeria.electroBalance.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repo.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with the given username");
        }
         
        return new CustomUserDetails(user);
	}

}
