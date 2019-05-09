package com.ffq.jta;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TCCController {
	@Autowired
	private TCCService tccService;
	
	@RequestMapping("/tcc")
	public String tcc() {
		tccService.tcc();
		return "tcc:" + LocalDateTime.now().toString();
	}
}
