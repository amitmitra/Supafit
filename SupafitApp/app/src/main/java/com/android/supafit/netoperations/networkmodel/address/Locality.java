package com.android.supafit.netoperations.networkmodel.address;

import java.io.Serializable;

public class Locality implements Serializable{

	private long id;
	private long cityId;
	private String name;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
