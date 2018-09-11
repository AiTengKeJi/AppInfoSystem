package com.appinfo.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.appinfo.entity.AppInfo;
import com.appinfo.entity.DevUser;
import com.appinfo.service.app.AppInfoService;
import com.appinfo.service.category.CategoryService;
import com.appinfo.service.dictionary.DictionaryService;
import com.appinfo.service.version.VersionService;
import com.appinfo.utils.CommonString;
import com.appinfo.utils.PageUtil;
import com.appinfo.entity.Version;


@Controller
@RequestMapping("/dev")
public class DevelopmentControl {
	
	@Autowired
	AppInfoService appInfoService; //AppInfo业务类
	@Autowired
	VersionService versionService;	//版本业务类
	@Autowired
	CategoryService categoryService;	//app分类业务
	@Autowired
	DictionaryService dictionaryService;//下拉框业务
	
		
	/*=======================跳转到开发人员首页========================*/
	@RequestMapping("/main")
	public String toDevMain() {
		return "/developer/main";
	}
	
	/*=======================APP维护信息列表========================*/
	/**显示所有数据功能正确
	 * 当前状态：已完成
	 * 负责人：李凯
	 * @return
	 */
	@RequestMapping("/app/list")
	public String getAppList(Model model,HttpSession session,
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
		page.setTotalCount(appInfoService.getAppInfoCount(softName, queryStatus, queryFlatformId, 
														category1, category2, category3));
		page.setTotalPageCount(page.getTotalCount()%rows==0?
							page.getTotalCount()/rows:(page.getTotalCount()/rows)+1);
		int offset = (pageIndex-1)*rows;//起始偏移量
		Integer devId = ((DevUser)session.getAttribute(CommonString.DEV_USER_SESSION)).getId();
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
		return "developer/appinfolist";
	}
	
	/*=======================根据parentId查询分类列表========================*/
	@RequestMapping("/categorylevellist.json")
	public void getCategoryByParentId(@RequestParam Integer pid,HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().print(JSONObject.toJSONString(categoryService.getCategoryByParentId(pid)));	
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
		model.addAttribute("appInfo",appInfoService.getAppInfoById(appId));
		model.addAttribute("appVersionList",versionService.getVersionList(appId));
		return "developer/appinfoview";
	}
	
	/*=======================修改APP信息========================*/
	/**跳转到修改app页面
	 * 当前状态：待完成
	 * 负责人：陈小聪
	 * @param appId
	 * @param model
	 * @return
	 */
	@RequestMapping("/app/toModify/{appId}")
	public String toModifyAppInfo(@PathVariable Integer appId,Model model) {
		model.addAttribute("flatFormList",dictionaryService.queryByTypeCode(CommonString.APP_FLATFORM));
		model.addAttribute("appInfo",appInfoService.getAppInfoById(appId));
		return "developer/appinfomodify";
	}
	/**修改APP信息
	 * 当前状态：待完成
	 * 负责人：陈小聪
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/app/doModify")
	public String doModify(@ModelAttribute AppInfo appInfo,HttpServletRequest req,
			@RequestParam(value="attach",required=false) MultipartFile attach) {
		HttpSession session = req.getSession();
		boolean isSuccess = true;
		String msg = "";
		//获取修改者对象
		DevUser createUser = (DevUser) session.getAttribute(CommonString.DEV_USER_SESSION);
		appInfo.setModifyBy(createUser.getId());
		//获取上传的文件名
		String oldName = attach.getOriginalFilename();
		//如果文件名不为空则进行文件上传
		if(!oldName.equals("")) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String suffixName = FilenameUtils.getExtension(oldName);//文件后缀名
			int fileSize = 500*1024;
			if(attach.getSize()>fileSize) { 
				msg = "*上传文件大小不得超过500kb";
			}else if(CommonString.checkLogoSuffixName(suffixName,CommonString.LOGO_SUFFIX_NAMES)) {
				String fileName = appInfo.getAPKName()+".jpg";
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()) 
					targetFile.mkdirs();
				String logoPicPath = req.getContextPath()+"/statics/uploadfiles/"+fileName;
				String logoLocPath = path+File.separator+fileName;
				appInfo.setLogoPicPath(logoPicPath);
				appInfo.setLogoLocPath(logoLocPath.replace('\\', '/'));
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					isSuccess = false;
					e.printStackTrace();
				}
			}else {
				msg ="图片格式不正确！（jpg、png、jpeg、pneg）";
			}
		}
		req.setAttribute("fileUploadError", msg);
		if(appInfoService.modify(appInfo)>0 && isSuccess) {
			return "redirect:/dev/app/list";
		}
		return "redirect:/dev/app/toModify/"+appInfo.getId();
	}
	
	@RequestMapping("/delLogo.json")
	public void deleteLogoFile(@RequestParam Integer id,HttpServletResponse resp,
								@RequestParam String filePath) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		String msg = "false";
		File file = new File(filePath);
		if(appInfoService.deleteAppLogo(id)>0){
			if(file.exists()) 
				file.delete();
			msg="true";
		}
		resp.getWriter().print(msg);
	}
	/*======================删除APP信息========================*/
	/**删除APP信息
	 * 当前状态：待完成
	 * 负责人：李凯
	 */
	@RequestMapping("/app/toDelete/{appId}")
	public String delAppInfo(@PathVariable Integer appId,Model model) {
		return "developer/appinfomodify";
	}
	
	/*=======================跳转到添加APP信息页面========================*/
	/**
	 * 当前状态：已完成
	 * 负责人：陈小聪
	 */
	@RequestMapping("/app/toAdd")
	public String toAddApp(Model model) {
		model.addAttribute("flatFormList",dictionaryService.queryByTypeCode(CommonString.APP_FLATFORM));
		model.addAttribute("categoryLevel1List",categoryService.getCategoryByParentId(0));
		return "developer/appinfoadd";
	}
	
	
	/*=======================JSON验证APKName是否存在========================*/
	/**
	 * 当前状态：已完成
	 * 负责人：陈小聪
	 * @throws IOException 
	 */
	@RequestMapping("/app/apkexist.json")
	public void checkAPKName(@RequestParam String APKName,HttpServletResponse resp) throws IOException {
		String msg = appInfoService.checkAPKName(APKName)>1?"exist":"notExist";
		resp.getWriter().print(msg);
	}
	
	/*=======================新增APP信息========================*/
	/**
	 * 当前状态：已完成
	 * 负责人：陈小聪
	 * @throws IOException 
	 */
	@RequestMapping("/app/doAdd")
	public String doAddAppInfo(@ModelAttribute AppInfo appInfo,HttpServletRequest req,
		@RequestParam(value="a_logoPicPath",required=false) MultipartFile file) throws IOException {
		HttpSession session = req.getSession();
		String path = session.getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		String suffixName = FilenameUtils.getExtension(file.getOriginalFilename());//文件后缀名
		int fileSize = 500*1024;
		if(file.getSize()>fileSize) { 
			req.setAttribute("fileUploadError", "*上传文件大小不得超过500kb");
		}else if(CommonString.checkLogoSuffixName(suffixName,CommonString.LOGO_SUFFIX_NAMES)) {
			String fileName = appInfo.getAPKName()+".jpg";
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) {
				targetFile.mkdirs();
			}
			DevUser devUser = (DevUser)session.getAttribute(CommonString.DEV_USER_SESSION);
			String logoPicPath = req.getContextPath()+"/statics/uploadfiles/"+fileName;
			String logoLocPath = path+File.separator+fileName;
			appInfo.setCreatedBy(devUser.getId());
			appInfo.setLogoPicPath(logoPicPath);
			appInfo.setLogoLocPath(logoLocPath.replace('\\', '/'));
			appInfo.setDevId(devUser.getId());
			appInfo.setStatus(1);
			//上传图片
			if(appInfoService.saveAppInfo(appInfo)>0) {
				file.transferTo(targetFile);
				return "redirect:/dev/app/list";
			}
		}else {
			req.setAttribute("fileUploadError","图片格式不正确！（jpg、png、jpeg、pneg）");
		}
		return "developer/appinfoadd";
	}

	/*=======================删除APP信息========================*/
	@RequestMapping("/app/delete.json")
	public void deleteApp(@RequestParam Integer id,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		boolean flag = false;
		if(appInfoService.deleteAppInfoById(id)>0 && versionService.deleteVersionByAppId(id)>0)
			flag=true;
		resp.getWriter().print(flag);
	}
	
	/*=======================App版本模块========================*/
	//跳转到添加app版本页面
	@RequestMapping("/version/toAddVersion/{appId}")
	public String toAddVersion(@PathVariable Integer appId,Model model) {
		Version version = new Version();
		version.setAppId(appId);
		version.setAppName(appInfoService.getAppInfoById(appId).getSoftwareName());
		model.addAttribute("appVersion",version);
		model.addAttribute("appVersionList",versionService.getVersionList(appId));
		return "developer/appversionadd";
	}
	//新增app版本信息
	@RequestMapping("/version/doAddversion")
	public String doAddVersion(@ModelAttribute Version version,Model model,HttpServletRequest req,
			@RequestParam(value="a_downloadLink",required=false) MultipartFile attach) {
		HttpSession session = req.getSession();
		String msg = "";
		DevUser devUser = (DevUser) session.getAttribute(CommonString.DEV_USER_SESSION);
		version.setCreatedBy(devUser.getId());
		//文件上传
		String path = session.getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
		String fileName = attach.getOriginalFilename();
		String suffixName = FilenameUtils.getExtension(fileName);//文件后缀名
		version.setApkFileName(fileName);
		version.setVersionSize((attach.getSize()/1024/1024.0));
		int fileSize = 500*1024*1024;
		boolean flag = false;
		if(attach.getSize()>fileSize) { 
			msg = "*上传文件大小不得超过500M";
		}else if(CommonString.checkLogoSuffixName(suffixName,CommonString.APK_SUFFIX_NAMES)) {
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()) 
				targetFile.mkdirs();
			String logoPicPath = req.getContextPath()+"/statics/uploadfiles/"+fileName;
			String logoLocPath = path+File.separator+fileName;
			version.setDownloadLink(logoPicPath);
			version.setApkLocPath(logoLocPath.replace('\\', '/'));
			try {
				attach.transferTo(targetFile);
				flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			msg ="文件格式不正确！（只能上传.apk文件）";
		}
		model.addAttribute("fileUploadError",msg);
		if(flag == true && versionService.addVersion(version)>0){
			return "redirect:/dev/app/list";
		}else {
			return "developer/appversionadd";
		}
	}
	
	//跳转到修改App版本信息页面
	@RequestMapping("/version/modify/{versionId}/{appId}")
	public String toModifyVersion(@PathVariable Integer versionId,
			@PathVariable Integer appId,Model model) {
		model.addAttribute("appVersionList",versionService.getVersionList(appId));
		model.addAttribute("appVersion",versionService.getVersionById(versionId));
		return "developer/appversionmodify";
	}
	
	//修改App版本信息
	@RequestMapping("/version/doModify")
	public String doModifyVersion(@ModelAttribute Version version,HttpServletRequest req,
			@RequestParam(value="attach",required=false) MultipartFile attach) {
		HttpSession session = req.getSession();
		boolean isSuccess = true;
		String msg = "";
		//获取修改者对象
		DevUser createUser = (DevUser) session.getAttribute(CommonString.DEV_USER_SESSION);
		version.setModifyBy(createUser.getId());
		version.setVersionSize((attach.getSize()/1024/1024.0));
		//获取上传的文件名
		String fileName = attach.getOriginalFilename();
		//如果文件名不为空则进行文件上传
		if(!fileName.equals("") && !fileName.equals(version.getApkFileName())) {
			String path = session.getServletContext().getRealPath("statics"+File.separator+"uploadfiles");
			String suffixName = FilenameUtils.getExtension(fileName);//文件后缀名
			int fileSize = 500*1024*1024;
			if(attach.getSize()>fileSize) { 
				msg = "*上传文件大小不得超过500M";
			}else if(CommonString.checkLogoSuffixName(suffixName,CommonString.APK_SUFFIX_NAMES)) {
				File targetFile = new File(path,fileName);
				if(!targetFile.exists()) 
					targetFile.mkdirs();
				String logoPicPath = req.getContextPath()+"/statics/uploadfiles/"+fileName;
				String logoLocPath = path+File.separator+fileName;
				version.setApkFileName(fileName);
				version.setDownloadLink(logoPicPath);
				version.setApkLocPath(logoLocPath.replace('\\', '/'));
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					isSuccess = false;
					e.printStackTrace();
				}
			}else {
				msg ="版本文件格式不正确！（目前只支持.apk格式）";
			}
		}
		req.setAttribute("fileUploadError", msg);
		if(versionService.modifyVersion(version)>0 && isSuccess) {
			return "redirect:/dev/app/list";
		}
		return "redirect:/dev/version/modify/"+version.getId()+"/"+version.getAppId();
	}
	//删除apk版本文件
	@RequestMapping("/delApkFile.json")
	public void delApkFile(HttpServletResponse resp,
			@RequestParam String filePath,@RequestParam Integer id) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		String msg = "false";
		File file = new File(filePath);
		if(versionService.updateVersionFileById(id)>0){
			if(file.exists()) 
				file.delete();
			msg="true";
		}
		resp.getWriter().print(msg);
	}
	
	/*=======================上下架========================*/
	@RequestMapping("/app/{appId}/{versionId}/sale.json")
	public void upperAndLower(@PathVariable Integer appId,@PathVariable Integer versionId,
			HttpSession session,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		Integer modifyId = ((DevUser)session.getAttribute(CommonString.DEV_USER_SESSION)).getId();
		resp.getWriter().print(appInfoService.upperAndLower(appId, modifyId,versionId));
	}
}
