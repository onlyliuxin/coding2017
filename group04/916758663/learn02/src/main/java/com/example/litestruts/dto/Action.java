package com.example.litestruts.dto;

import java.util.List;

/**
 * Created by qilei on 17/3/5.
 */
public class Action {
  private String name;
  private String className;
  private List<ActionResult> actionResultList;

  public Action() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public List<ActionResult> getActionResultList() {
    return actionResultList;
  }

  public void setActionResultList(List<ActionResult> actionResultList) {
    this.actionResultList = actionResultList;
  }

  private Action(Builder builder) {
    name = builder.name;
    className = builder.className;
    actionResultList = builder.actionResultList;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {

    private String name;
    private String className;
    private List<ActionResult> actionResultList;

    private Builder() {
    }

    public Builder name(String val) {
      name = val;
      return this;
    }

    public Builder className(String val) {
      className = val;
      return this;
    }

    public Builder actionResultList(List<ActionResult> val) {
      actionResultList = val;
      return this;
    }

    public Action build() {
      return new Action(this);
    }
  }
}
