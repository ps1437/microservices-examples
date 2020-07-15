package com.syscho.auth.config.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.syscho.auth.config.entity.UserAuthDetails;
import com.syscho.auth.config.entity.UserDO;
import com.syscho.auth.config.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserDO> optionalUser = repository.findByUsername(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
		UserDO user = optionalUser.get();

		return new UserAuthDetails(user);
	}

}
