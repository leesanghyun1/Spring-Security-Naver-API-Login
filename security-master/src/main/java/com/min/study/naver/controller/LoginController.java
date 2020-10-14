package com.min.study.naver.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
 
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.min.study.user.vo.UserDetailsVO;

/**
 * Handles requests for the application home page.
 */

@Resource(name="UserAuthenticationService")

@Controller
public class LoginController {

    /* NaverLoginBO */
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;
    
    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }

    //로그인 첫 화면 요청 메소드
    @RequestMapping(value = "user/loginPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String login(Model model, HttpSession session) {
    	System.out.println("-----네이버 로그인 화면 요청-----");
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버: " + naverAuthUrl);
        
        //네이버 
        model.addAttribute("url", naverAuthUrl);

        /* 생성한 인증 URL을 View로 전달 */
        return "user/loginPage";
    }

    //네이버 로그인 성공시 callback호출 메소드
    @RequestMapping(value = "/oauth2/callback", method = { RequestMethod.GET, RequestMethod.POST })
    public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, HttpServletRequest request)
            throws IOException {
    	System.out.println("---------네이버 로그인 callback -----------");
        OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        
        //로그인 사용자 정보를 읽어온다.
        apiResult = naverLoginBO.getUserProfile(oauthToken);
        System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
        model.addAttribute("result", apiResult);
        System.out.println("result: "+apiResult);
        
        
        //security 강제 권한부여
        System.out.println("--------- 권한 부여 -----------");
        List<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        auth.add(new SimpleGrantedAuthority("ROLE_GUEST"));
        new UserDetailsVO("naver", "naver", "naver", true, true, true, true, auth , "naver");
        Authentication authentication = new UsernamePasswordAuthenticationToken("naver", "naver", auth);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session1 = request.getSession(true);
        session1.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        
        return "naverSuccess";
    }
}
