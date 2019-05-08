package com.ffq.login;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffq.user.UserService;
import com.google.common.collect.Maps;

@RestController
public class LoginController {
	@Autowired
	private LoginUtil loginUtil;
	
	@Autowired
	private UserService userService;
	
	/**
	 * @MethodName login
	 * @Description 登录后返回接口凭据token和前端菜单显示码code
	 * @param uid
	 * @returnuid
	 */
	@RequestMapping("/{uid}/login")
	public Map<String,Object> login(@PathVariable("uid") String uid) {
		Map<String,Object> map = Maps.newHashMap();
		map.put("token", loginUtil.createToken(uid));
		map.put("code", userService.getCodeByUserId(uid));
		return map;
	}
}	
