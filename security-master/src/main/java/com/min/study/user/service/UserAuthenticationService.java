package com.min.study.user.service;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.min.study.user.vo.UserDetailsVO;

public class UserAuthenticationService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
	private SqlSessionTemplate sqlSession;
	
	
	
	public UserAuthenticationService() {
		// TODO Auto-generated constructor stub
	}

	public UserAuthenticationService(SqlSessionTemplate sqlSession) {
		// TODO Auto-generated constructor stub
		this.sqlSession = sqlSession;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//mybatis를 이용하여 디비값 가져옴
		Map<String, Object> user = sqlSession.selectOne("user.selectUser",username); 
		
		//유저없을 경우 처리
		if(user ==null) {
			System.out.println(username+"이라는 유저는 존재 하지 않음 ");
			throw new UsernameNotFoundException(username);
			}
		logger.info("------유저정보:"+user.toString());
		List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
		gas.add(new SimpleGrantedAuthority(user.get("AUTHORITY").toString()));
		
		//오라클 number타입 int로 변환
		String en = String.valueOf(user.get("ENABLED")); 
		int enabled = Integer.parseInt(en);
		
		//List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        //gas.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		//new UserDetailsVO("naver", "naver", "naver", true, true, true, true, gas , "naver");
		return new UserDetailsVO(user.get("USERNAME").toString(), user.get("PASSWORD").toString(),user.get("NAME").toString() ,  enabled == 1, true, true, true, gas,user.get("USERNAME").toString());
	}

}
