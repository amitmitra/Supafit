package com.android.supafit.model.networkmodel;

/**
 * Created by harsh on 3/30/16.
 */
public class Food {
  /*
   {
      "id": 1,
      "cuisine": "North India",
      "description": "North Indian style food"
    }
  */
  long id;
  String cuisine;
  String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCuisine() {
    return cuisine;
  }

  public void setCuisine(String cuisine) {
    this.cuisine = cuisine;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
