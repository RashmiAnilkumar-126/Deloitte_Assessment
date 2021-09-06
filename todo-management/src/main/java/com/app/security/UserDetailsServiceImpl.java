package com.app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.Users;
import com.app.repository.UserDetailsRepository;

@Service("c_user_details")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailsRepository userDetailsRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Users> _user = userDetailsRepo.findByUserName(username);
		if (!_user.isPresent())
			throw new UsernameNotFoundException("Username is not found.");
		
		Users users = _user.get();
		return User.builder().username(users.getPassword()).password(bCryptPasswordEncoder.encode(users.getPassword()))
				.roles(users.getUserRole()).build();
	}

}
