package com.syscho.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syscho.user.service.UserService;
import com.syscho.user.vo.UserVO;

@RestController
@RequestMapping("/users")
public class UserController {


	@Autowired
	Environment environment;
	UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<UserVO> getAllReaders() {

		return service.getUsers();
	}

	@GetMapping(path="/{userName}")
	public UserVO getUser(@PathVariable("userName") String userName) {

		System.out.println("UserController.getUser()" +environment.getProperty("jwt.secret.key"));
		return service.getUserByName(userName);
	}

	@PostMapping(path = "/signup")
	public ResponseEntity<String> createUser(@RequestBody UserVO userVO) {
		boolean result = service.createUser(userVO);

		if (result) {
			return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed to create user", HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(path = "/update")
	public ResponseEntity<UserVO> updateUser(@RequestBody UserVO userVO) {
		UserVO result = service.updateUser(userVO);
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
