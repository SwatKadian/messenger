package org.webservices.restapi.messenger.resources.beans;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	private @QueryParam("year") int yr;
	private @QueryParam("start") int st;
	private @QueryParam("size") int sz;
	 
	public int getYr() {
		return yr;
	}
	public void setYr(int yr) {
		this.yr = yr;
	}
	public int getSt() {
		return st;
	}
	public void setSt(int st) {
		this.st = st;
	}
	public int getSz() {
		return sz;
	}
	public void setSz(int sz) {
		this.sz = sz;
	}
	
	
}
