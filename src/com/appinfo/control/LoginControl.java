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
	/* =======================登录======================== */
	/**
	 * 跳转到开发者登录页面 当前状态：已完成 操作人：陈小聪
	 * @return
	 */
	@RequestMapping("/developer")
	public String toDevLogin() {
		return "devlogin";
	}

	/**
	 * 开发人员登录验证 
	 * 当前状态：已完成 
	 * 操作人：廖立挺
	 * @return
	 */
	@RequestMapping("/developer/doLogin")
	public String doDevLogin(@RequestParam String devCode, @RequestParam String devPassword,
			HttpServletRequest req) {
		// 登录错误提示
		String msg = "";
		DevUser devUser = null;
		if (devUserService.checkDevUserCode(devCode) > 0) {
			devUser = devUserService.devLogin(devCode, devPassword);
			if (devUser != null) {
				req.getSession().setAttribute(CommonString.DEV_USER_SESSION, devUser);
			} else {
				msg = "密码错误！";
			}
		} else {
			msg = "用户名不存！";
		}
		req.setAttribute("error", msg);
		if(msg.equals(""))
			return "redirect:/dev/main";
		else
			return "devlogin";
	}
	//开人员退出登录
	@RequestMapping("/developer/logout")
	public String devLoginOut(HttpSession session) {
		session.removeAttribute(CommonString.DEV_USER_SESSION);
		return "devlogin";
	}
	
	// 跳转到管理员登录页面
	@RequestMapping("/backend")
	public String toAdminLogin() {
		return "backendlogin";
	}

	/**
	 * 管理员登录验证 
	 * 当前状态：已完成 
	 * 负责人：廖立挺
	 * @return
	 */
	@RequestMapping("/backend/doLogin")
	public String doAdminLogin(@RequestParam String userCode, @RequestParam String userPassword,
			HttpServletRequest req) {
		// 登录错误提示
		String msg = "";
		BackendUser backUser = null;
		if (backUserService.checkUserCode(userCode) > 0) {
			backUser = backUserService.checkLogin(userCode, userPassword);
			if (backUser != null) {
				req.getSession().setAttribute(CommonString.USER_SESSION, backUser);
			} else {
				msg = "密码错误！";
			}
		} else {
			msg = "用户名不存！";
		}
		req.setAttribute("error", msg);
		if(msg.equals(""))
			return "redirect:/admin/main";
		else 
			return "backendlogin";
	}
	
	//开人员退出登录
	@RequestMapping("/backend/logout")
	public String adminLoginOut(HttpSession session) {
		session.removeAttribute(CommonString.USER_SESSION);
		return "backendlogin";
	}
}
