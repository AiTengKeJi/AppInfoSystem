package com.appinfo.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.appinfo.entity.BackendUser;
import com.appinfo.entity.DevUser;
import com.appinfo.utils.CommonString;


public class LoginCheckHandlerInterceptor extends HandlerInterceptorAdapter   {
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HttpSession session = request.getSession();
		
		BackendUser backendUser = (BackendUser)session.getAttribute(CommonString.USER_SESSION);
		DevUser devUser = (DevUser)session.getAttribute(CommonString.DEV_USER_SESSION);
		
		if(null != devUser){ //dev SUCCESS
			return true;
		}else if(null != backendUser){ //backend SUCCESS
			return true;
		}else{
			response.sendRedirect(request.getContextPath()+"/403.jsp");
			return false;
		}
		
	}

}
