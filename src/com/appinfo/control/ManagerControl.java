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
	
	//跳转到管理员登录页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "backendlogin";
	}
	
	/**
	 * 管理员登录验证 
	 * 当前状态：已完成
	 * 负责人：廖立挺
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String userCode,
						  @RequestParam String userPassword,
						  HttpServletRequest req) {
		//登录错误提示
		String msg = "";
		BackendUser backUser = null;
		if(backUserService.checkUserCode(userCode)>0) {
			backUser = backUserService.checkLogin(userCode, userPassword);
			if(backUser!=null) {
				req.getSession().setAttribute("userSession", backUser);
			}else {
				msg="密码错误！";
			}
		}else {
			msg="用户名不存！";
		}
		req.setAttribute("error",msg);
		return "backend/main";
	}
	
	
}
