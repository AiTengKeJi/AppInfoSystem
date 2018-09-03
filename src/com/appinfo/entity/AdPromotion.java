package com.appinfo.entity;

public class AdPromotion {
	private Integer id;
	private Integer appId;
	private String adPicPath;
	private Integer adPV;
	private Integer carouselPosition;
	private String startTime;
	private String endTime;
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
	public String getAdPicPath() {
		return adPicPath;
	}
	public void setAdPicPath(String adPicPath) {
		this.adPicPath = adPicPath;
	}
	public Integer getAdPV() {
		return adPV;
	}
	public void setAdPV(Integer adPV) {
		this.adPV = adPV;
	}
	public Integer getCarouselPosition() {
		return carouselPosition;
	}
	public void setCarouselPosition(Integer carouselPosition) {
		this.carouselPosition = carouselPosition;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
