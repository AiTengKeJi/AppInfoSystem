package com.appinfo.entity;

public class Version {
	private Integer id;
	private Integer appId;
	private String versionInfo;
	private String versionNo;
	private Integer publishStatus;
	private String downloadLink;
	private double versionSize;
	private Integer createdBy;
	private String creationDate;
	private Integer modifyBy;
	private String publishStatusName;       //状态
	private String appName;
	private String modifyDate;
	private String apkFileName;
	private String apkLocPath;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public Integer getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	public double getVersionSize() {
		return versionSize;
	}
	public void setVersionSize(double versionSize) {
		this.versionSize = versionSize;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getPublishStatusName() {
		return publishStatusName;
	}
	public void setPublishStatusName(String publishStatusName) {
		this.publishStatusName = publishStatusName;
	}
	
	public String getApkFileName() {
		return apkFileName;
	}
	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getApkLocPath() {
		return apkLocPath;
	}
	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
}
