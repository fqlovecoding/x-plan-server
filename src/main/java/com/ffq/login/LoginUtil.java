package com.ffq.login;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ffq.user.UserService;
import com.google.common.collect.Lists;

@Component
public class LoginUtil {
	@Autowired
	private UserService userService;
	
	/**
	 * @MethodName createToken
	 * @Description 用户登录后生成token
	 * @param userId
	 * @return
	 */
	public String createToken(String uid) {
		return JWT.create().withAudience(uid).sign(Algorithm.HMAC256(uid));
	}

	/**
	 * @MethodName parseToken
	 * @Description 根据用户id解析token
	 * @param req
	 * @return
	 */
	public String parseToken(HttpServletRequest req) {
		String token = req.getHeader("X-AUTH-TOKEN");
		if (token == null) {
			return null;
		}
		String uid = JWT.decode(token).getAudience().get(0);
		if (uid == null) {
			return null;
		}
		return uid;
	}
	
	/***
	 * @MethodName auth
	 * @Description 鉴权过程
	 * @param req
	 * @param resp
	 * @return
	 */
	public boolean auth(HttpServletRequest req,HttpServletResponse resp) {
		//排除不需要token的接口
		String url = req.getRequestURI();
		if(getPassList().contains(url)) {
			return true;
		}
		//解析token是否存在
		String uid = parseToken(req);
		if(uid == null) {
			return false;
    	}
		//根据解析结果判定接口权限
		if(userService.pass(uid, url)) {
			return true;
		}
		return false;
	}

	/**
	 * @MethodName getPassList
	 * @Description 获取不需要鉴权的接口
	 * @return
	 */
	private List<String> getPassList(){
		return Lists.newArrayList("/1/login","/tcc","/1/3/horder","/1/9/order");
	}
}
