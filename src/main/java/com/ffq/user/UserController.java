package com.ffq.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/tcc")
	public String tcc() {
		userService.tcc();
		return "tcc:" + LocalDateTime.now().toString();
	}
}
