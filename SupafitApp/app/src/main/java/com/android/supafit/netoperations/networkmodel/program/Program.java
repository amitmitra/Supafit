package com.android.supafit.netoperations.networkmodel.program;

import java.io.Serializable;

public class Program implements Serializable{

	private long id;
	private long categoryId;
	private long typeId;
	private String name;
	private String title;
	private String description;
	private int numberOfCoaches;
	private String cost;
	private String expectedResult;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfCoaches() {
		return numberOfCoaches;
	}
	public void setNumberOfCoaches(int numberOfCoaches) {
		this.numberOfCoaches = numberOfCoaches;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
}
