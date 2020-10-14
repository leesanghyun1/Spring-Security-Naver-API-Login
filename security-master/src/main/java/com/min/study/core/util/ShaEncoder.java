package com.min.study.core.util;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("shaEncoder")
public class ShaEncoder {
	
	@Resource(name="passwordEncoder")
	private ShaPasswordEncoder encoder;
	
	//SHA256 암호화
	public String encoding(String str){
		System.out.println("-----str:"+str);
		return encoder.encodePassword(str,null);
	}
	
	//SALT 암호화
	public String saltEncoding(String str,String salt){
		System.out.println("-----str:"+str);
		System.out.println("-----salt:"+salt);
		return encoder.encodePassword(str,salt);
	}
}
