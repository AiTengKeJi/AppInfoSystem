package com.appinfo.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appinfo.entity.BackendUser;
import com.appinfo.entity.DevUser;
import com.appinfo.service.user.BackendUserServiceImpl;
import com.appinfo.service.user.DevUserService;
import com.appinfo.utils.CommonString;

@Controller
@RequestMapping("/login")
public class LoginControl {

	@Autowired
	BackendUserServiceImpl backUserService;
	@Autowired
	DevUserService devUserService;
	
	
	
}
