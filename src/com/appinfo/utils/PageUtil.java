package com.appinfo.utils;

public class PageUtil {
	
	private Integer totalCount;	//总行数
	private Integer currentPageNo;//当前页
	private Integer totalPageCount;//总页数
	
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	/**
	 * 每页显示行数
	 * @author naruto
	 *
	 */
	public interface Rows{
		int APPINFO_ROWS = 5;
		int BILL_ROWS = 10;
		int PROVIDER_ROWS = 10;
	}
}
