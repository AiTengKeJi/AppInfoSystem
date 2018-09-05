package com.appinfo.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appinfo.entity.Category;
import com.appinfo.service.appinfo.AppInfoService;
import com.appinfo.service.category.CategoryService;
import com.appinfo.service.version.VersionService;
import com.appinfo.utils.PageUtil;

@Controller
@RequestMapping("/dev")
public class DevelopmentControl {
	
	@Autowired
	AppInfoService appInfoService; //AppInfo业务类
	@Autowired
	VersionService versionService;	//版本业务类
	@Autowired
	CategoryService categoryService;	//app分类业务
	
	
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
		@RequestParam(value="queryStatus",required=false) Integer queryStatus,
		@RequestParam(value="queryFlatformId",required=false) Integer queryFlatformId,
		@RequestParam(value="queryCategoryLevel1",required=false) Integer category1,
		@RequestParam(value="queryCategoryLevel2",required=false) Integer category2,
		@RequestParam(value="queryCategoryLevel3",required=false) Integer category3) {
		PageUtil page = new PageUtil();
		pageIndex = pageIndex==null?1:pageIndex;
		page.setCurrentPageNo(pageIndex);
		
		
		
		
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
		return "developer/appinfolist";
	}
	
	/*=======================根据parentId查询分类列表========================*/
	@RequestMapping("/categorylevellist.json")
	@ResponseBody
	public List<Category> getCategoryByParentId(@RequestParam Integer pid){
		return categoryService.getCategoryByParentId(pid);
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
	
	/*=======================修改APP信息========================*/
	/**修改APP信息
	 * 当前状态：待完成
	 * 负责人：陈小聪
	 * @param appId
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/toModify/{appId}")
	public String toModifyAppInfo(@PathVariable Integer appId,Model model) {
		
		return "developer/appinfomodify";
	}
	
	/*======================删除APP信息========================*/
	/**删除APP信息
	 * 当前状态：待完成
	 * 负责人：李凯
	 */
	@RequestMapping("/app/toModify/{appId}")
	public String delAppInfo(@PathVariable Integer appId,Model model) {
		
		return "developer/appinfomodify";
	}
	
	
	
}
