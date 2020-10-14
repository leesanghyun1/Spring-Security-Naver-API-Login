package com.min.study.user.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.min.study.user.vo.UserDetailsVO;


public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(UserLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("-----------로그인성공-----------");
		UserDetailsVO u = (UserDetailsVO) auth.getPrincipal();
		logger.info("----getname: "+auth.getName());
		logger.info("----getauth: "+auth.getAuthorities().toString());
		logger.info("----getdetail: "+auth.getDetails().toString());
		logger.info("----getprincipal: "+auth.getPrincipal().toString());
		for(GrantedAuthority a : auth.getAuthorities()){
			logger.info(a.getAuthority());
		}
		logger.info(String.valueOf(u.isAccountNonExpired()));
		logger.info(String.valueOf(u.isAccountNonLocked()));
		logger.info(String.valueOf(u.isCredentialsNonExpired()));
		logger.info(String.valueOf(u.isEnabled()));
		logger.info("---redirect:"+req.getContextPath());
		res.sendRedirect(req.getContextPath()+"/");
	}

}
