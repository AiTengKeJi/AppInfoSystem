package com.appinfo.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class DevelopmentControl {
	//跳转到开发人员登录页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "devlogin";
	}
		
	//开发人员登录验证
	@RequestMapping("/doLogin")
	public String doLogin() {
		return "devlogin";
	}
}
