package com.android.supafit.model.networkmodel;

/**
 * Created by harsh on 3/31/16.
 */
public class MedicalCondition {
  /*
  {
    "id": 1,
    "condition": "Hypertension",
    "description": "Hypertension"
  }
  */

  long id;
  String condition;
  String description;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
