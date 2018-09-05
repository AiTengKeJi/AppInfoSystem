package com.appinfo.control;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appinfo.entity.BackendUser;
import com.appinfo.service.user.BackendUserServiceImpl;

@Controller
public class LoginControl {
	
	
	@Autowired
	BackendUserServiceImpl backUserService;
	
	
	/*=======================登录========================*/
	/**跳转到开发者登录页面
	 * 当前状态：已完成
	 * 操作人：陈小聪
	 * @return
	 */
	@RequestMapping("/dev/toLogin")
	public String toDevLogin() {
		return "devlogin";
	}
		
	/**开发人员登录验证
	 * 当前状态：待完成
	 * 操作人：廖立挺
	 * @return
	 */
	@RequestMapping("/dev/doLogin")
	public String doDevLogin() {
		return "devlogin";
	}
	
	//跳转到管理员登录页面
		@RequestMapping("/toLogin")
		public String toAdminLogin() {
			return "backendlogin";
		}
		
		/**
		 * 管理员登录验证 
		 * 当前状态：已完成
		 * 负责人：廖立挺
		 * @return
		 */
		@RequestMapping("/doLogin")
		public String doAdminLogin(@RequestParam String userCode,
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
