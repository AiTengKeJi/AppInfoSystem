package com.appinfo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerControl {
	
	//跳转到管理员登录页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "backendlogin";
	}
	
	//管理员登录验证
	@RequestMapping("/doLogin")
	public String doLogin() {
		return "backendlogin";
	}
}
