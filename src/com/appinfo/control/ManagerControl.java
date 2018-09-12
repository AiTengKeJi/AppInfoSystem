package com.appinfo.control;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appinfo.entity.BackendUser;
import com.appinfo.service.app.AppInfoService;
import com.appinfo.service.category.CategoryService;
import com.appinfo.service.dictionary.DictionaryService;
import com.appinfo.service.user.BackendUserServiceImpl;
import com.appinfo.service.version.VersionService;
import com.appinfo.utils.CommonString;
import com.appinfo.utils.PageUtil;

@Controller
@RequestMapping("/admin")
public class ManagerControl {
	
	@Autowired
	BackendUserServiceImpl backUserService;
	@Autowired
	AppInfoService appInfoService; //AppInfo业务类
	@Autowired
	VersionService versionService;	//版本业务类
	@Autowired
	CategoryService categoryService;	//app分类业务
	@Autowired
	DictionaryService dictionaryService;//下拉框业务
	
	
	//跳转到管理员主页面
	@RequestMapping("/main")
	public String toBackendMain() {
		return "backend/main";
	}
	
	
	/*=======================跳转到applist页面========================*/
	@RequestMapping("manager/backend/app/list")
	public String getAppstatuslist(Model model,HttpSession session,
			@RequestParam(value="pageIndex",required=false) Integer pageIndex,
			@RequestParam(value="querySoftwareName",required=false) String softName,
			@RequestParam(value="queryStatus",required=false) Integer queryStatus,
			@RequestParam(value="queryFlatformId",required=false) Integer queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false) Integer category1,
			@RequestParam(value="queryCategoryLevel2",required=false) Integer category2,
			@RequestParam(value="queryCategoryLevel3",required=false) Integer category3) {
			PageUtil page = new PageUtil();
			int rows = PageUtil.Rows.APPINFO_ROWS;	//每页显示行数
			pageIndex = pageIndex==null?1:pageIndex;
			page.setCurrentPageNo(pageIndex);
			queryStatus=1;
			page.setTotalCount(appInfoService.getAppInfoCount(softName, queryStatus, queryFlatformId, 
															category1, category2, category3));
			page.setTotalPageCount(page.getTotalCount()%rows==0?
								page.getTotalCount()/rows:(page.getTotalCount()/rows)+1);
			int offset = (pageIndex-1)*rows;//起始偏移量
			Integer devId = ((BackendUser)session.getAttribute(CommonString.USER_SESSION)).getId();
			model.addAttribute("statusList",dictionaryService.queryByTypeCode(CommonString.APP_STATUS));
			model.addAttribute("flatFormList",dictionaryService.queryByTypeCode(CommonString.APP_FLATFORM));
			model.addAttribute("pages",page);
			model.addAttribute("querySoftwareName",softName);//软件名称
			model.addAttribute("queryStatus",queryStatus);//App状态
			model.addAttribute("queryFlatformId",queryFlatformId);//所属平台
			model.addAttribute("queryCategoryLevel1",category1);
			model.addAttribute("categoryLevel1List",categoryService.getCategoryByParentId(0));
			model.addAttribute("queryCategoryLevel2",category2);
			model.addAttribute("categoryLevel2List",categoryService.getCategoryByParentId(category1));
			model.addAttribute("queryCategoryLevel3",category3);
			model.addAttribute("categoryLevel3List",categoryService.getCategoryByParentId(category2));
			model.addAttribute("appInfoList",appInfoService.getAppinfoList(softName, queryStatus,
					queryFlatformId, category1, category2, category3,
					rows, offset,devId));
			return "backend/applist";
	
}
	@RequestMapping("manager/backend/app/check")
	public String getAppstatuslist(@RequestParam Integer aid,@RequestParam Integer vid,Model model) {
		model.addAttribute("appInfo",appInfoService.getAppInfoById(aid));
		model.addAttribute("appVersion",versionService.getVersionById(vid));
		return "backend/appcheck";
	}
	@RequestMapping("manager/backend/app/checksave")
	public String getAppstatuslist(@RequestParam Integer id ,@RequestParam Integer status,HttpServletResponse resp) {
		appInfoService.updateSatus(id,status);
		return "redirect:list";
	}
	
}
