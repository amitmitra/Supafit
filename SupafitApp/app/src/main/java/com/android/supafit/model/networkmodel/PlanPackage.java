package com.android.supafit.model.networkmodel;

import java.io.Serializable;

/**
 * Created by harsh on 2/13/16.
 */
public class PlanPackage implements Serializable {

  private long id;
  private long categoryId;
  private long typeId;
  private String name;
  private String title;
  private String description;
  private int numberOfCoaches;
  private int cost;
  private String expectedResult;

  private long timestamp;

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

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public String getExpectedResult() {
    return expectedResult;
  }

  public void setExpectedResult(String expectedResult) {
    this.expectedResult = expectedResult;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(long timestamp) {
    this.timestamp = timestamp;
  }
}
