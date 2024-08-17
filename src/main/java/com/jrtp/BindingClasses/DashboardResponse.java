package com.jrtp.BindingClasses;

public class DashboardResponse {

	
	private Integer totalenquiriesCnt;
	
	private Integer enrolledCnt;
	
	
	

	private Integer lostCnt;

	public Integer getTotalenquiriesCnt() {
		return totalenquiriesCnt;
	}

	public void setTotalenquiriesCnt(Integer totalenquiriesCnt) {
		this.totalenquiriesCnt = totalenquiriesCnt;
	}

	public Integer getEnrolledCnt() {
		return enrolledCnt;
	}

	public void setEnrolledCnt(Integer enrolledCnt) {
		this.enrolledCnt = enrolledCnt;
	}

	public Integer getLostCnt() {
		return lostCnt;
	}

	public void setLostCnt(Integer lostCnt) {
		this.lostCnt = lostCnt;
	}
	
}
