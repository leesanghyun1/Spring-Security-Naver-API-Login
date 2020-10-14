package com.min.study.user.vo;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;

public class UserDetailsVO extends User{

	
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private String id;
	private String password;
	private String name;
	private Collection<? extends GrantedAuthority> authorities;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public UserDetailsVO(String username, String password,String name ,boolean enabled, boolean accountNonExpired, 
			boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,String id) {
		
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);	
		this.id = id; 
		this.name = name;
		this.authorities = authorities;
		// TODO Auto-generated constructor stub
		System.out.println("--------아이디:" + id);
		System.out.println("--------비밀번호:" + password);
		System.out.println("--------이름:" + name);
		System.out.println("--------권한:" + authorities);
	}
	
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "; id: "+this.id;
	}

}
