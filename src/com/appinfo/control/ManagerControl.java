package com.appinfo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.appinfo.service.user.BackendUserServiceImpl;

@Controller
@RequestMapping("/admin")
public class ManagerControl {
	
	@Autowired
	BackendUserServiceImpl backUserService;
	
	
	//跳转到管理员主页面
	@RequestMapping("/main")
	public String toBackendMain() {
		return "backend/main";
	}
	
	
	/*=======================跳转到applist页面========================*/
	
	
}
