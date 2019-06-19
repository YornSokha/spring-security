package com.hrd.springsecurity.custom_handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Null;
import java.io.IOException;

@Component
public class CustomSuccessHandler  implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//        httpServletResponse.sendRedirect("/");
//        String redirect = null;
//        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
//            if(grantedAuthority.getAuthority().equals("ROLE_USER")){
//                redirect = "/user";
//            }
//            else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")){
//                redirect = "/admin";
//            }
//        }
//        httpServletResponse.sendRedirect(redirect);
        try {
            String url = httpServletRequest.getSession().getAttribute("REDIRECT_URL").toString();
            httpServletResponse.sendRedirect(url);
        }catch (NullPointerException e){
            httpServletResponse.sendRedirect("/");
        }
    }
}
