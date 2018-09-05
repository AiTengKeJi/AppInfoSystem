package com.appinfo.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appinfo.entity.BackendUser;
import com.appinfo.service.user.BackendUserServiceImpl;

@Controller
@RequestMapping("/manager")
public class ManagerControl {
	
	@Autowired
	BackendUserServiceImpl backUserService;
	
	
	
	
	/*=======================跳转到applist页面========================*/
	
	
}
