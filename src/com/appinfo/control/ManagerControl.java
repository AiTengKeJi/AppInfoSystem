package com.appinfo.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager")
public class ManagerControl {
	
	//跳转到管理员登录页面
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "backendlogin";
	}
	
	/**
	 * 管理员登录验证 
	 * 是否完成：是
	 * 当前状态：等待跳转
	 * 操作人：李凯
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin(@RequestParam String userCode,
						  @RequestParam String userPassword,
						  Model model) {
		//登录错误提示
		String msg = "";
		
		
		model.addAttribute("error",msg);
		return "跳转到APP列表页面";
	}
	
	
}
