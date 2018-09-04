package com.appinfo.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.appinfo.service.info.InfoService;
import com.appinfo.service.version.VersionService;

@Controller
@RequestMapping("/dev")
public class DevelopmentControl {
	
	@Autowired
	InfoService appInfoService; //AppInfo业务类
	@Autowired
	VersionService versionService;	//版本业务类
	
	
	/*=======================登录========================*/
	/**跳转到开发者登录页面
	 * 当前状态：已完成
	 * 操作人：陈小聪
	 * @return
	 */
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "devlogin";
	}
		
	/**开发人员登录验证
	 * 当前状态：待完成
	 * 操作人：廖立挺
	 * @return
	 */
	@RequestMapping("/doLogin")
	public String doLogin() {
		return "devlogin";
	}
	
	/*=======================APP维护信息列表========================*/
	/**显示所有数据功能正确
	 * 当前状态：待完成
	 * 负责人：李凯
	 * @return
	 */
	@RequestMapping("/app/list")
	public String getAppList(Model model,
		@RequestParam(value="pageIndex",required=false) Integer pageIndex,
		@RequestParam(value="querySoftwareName",required=false) String softName,
		@RequestParam(value="queryStatus",required=false) String queryStatus,
		@RequestParam(value="queryFlatformId",required=false) String queryFlatformId,
		@RequestParam(value="queryCategoryLevel1",required=false) String category1,
		@RequestParam(value="queryCategoryLevel2",required=false) String category2,
		@RequestParam(value="queryCategoryLevel3",required=false) String category3) {
		
		
		
		
		
		model.addAttribute("querySoftwareName",softName);//软件名称
		model.addAttribute("queryStatus",queryStatus);//App状态
		model.addAttribute("queryFlatformId",queryFlatformId);//所属平台
		model.addAttribute("queryCategoryLevel1",category1);//1级分类
		model.addAttribute("queryCategoryLevel2",category2);//2级分类
		model.addAttribute("queryCategoryLevel3",category3);//3级分类
		return "developer/appinfolist";
	}
	
	/*=======================修改APP版本信息========================*/
	@RequestMapping("/version/toModify")
	public String  toAppVersionModify(@RequestParam Integer vid,
									  @RequestParam Integer aid) {
		return "developer/appversionmodify";
	}
	
	/*=======================查看APP信息========================*/
	/**查看APP信息
	 * 当前状态：待完成
	 * 操作人：李凯
	 * @param id
	 * @return
	 */
	@RequestMapping("/app/view/{appId}")
	public String  toAppView(@PathVariable Integer appId,Model model) {
		
		
		model.addAttribute("appInfo","根据appId查询AppInfo对象");
		model.addAttribute("appVersionList","根据appId查询版本记录（集合）");
		return "developer/appinfoview";
	}
	
	
}
