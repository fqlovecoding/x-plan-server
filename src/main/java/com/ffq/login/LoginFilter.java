package com.ffq.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.ffq.user.UserService;

@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
	private UserService userService;
	private LoginUtil loginUtil;
	
	/**
	 * OverridingName: doFilter Description: 所有接口都过滤
	 * 
	 * @param request
	 * @param response
	 * @param chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (!loginUtil.auth(req, resp)) {
			resp.getWriter().write("{NO AUTH:403}");
			return;
		}
		chain.doFilter(req, resp);
	}

	/**
	 * OverridingName: init  
	 * Description: 初始化时注入实例  
	 * @param fConfig
	 * @throws ServletException
	 */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(fConfig.getServletContext());
        if(cxt != null && cxt.getBean("userService") != null && userService == null) {
        	userService = (UserService) cxt.getBean("userService");        
        }
        if(cxt != null && cxt.getBean("loginUtil") != null && loginUtil == null) {
        	loginUtil = (LoginUtil) cxt.getBean("loginUtil");        
        }
    }
}
