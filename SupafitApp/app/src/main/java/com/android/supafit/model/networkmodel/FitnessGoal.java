package com.android.supafit.model.networkmodel;

/**
 * Created by harsh on 3/31/16.
 */
public class FitnessGoal {
  /*
  {
id (integer, optional),
goal (string, optional),
description (string, optional)
}
  */

  long id;
  String goal;
  String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getGoal() {
    return goal;
  }

  public void setGoal(String goal) {
    this.goal = goal;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
