package com.android.supafit.netoperations.networkmodel.exercise;

import java.io.Serializable;

public class Timing implements Serializable{

	private long id;
	private String start;
	private String end;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
