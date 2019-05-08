package com.ffq.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ffq.user.User;
import com.ffq.user.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Dao {

	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void test_0() {
		List<User> userList = userMapper.list();
		System.out.println(userList);
	}
}
