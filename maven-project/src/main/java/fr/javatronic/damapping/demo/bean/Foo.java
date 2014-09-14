package fr.javatronic.damapping.demo.bean;

import java.util.List;

public class Foo {
  private List<Footi> footis;
  /**
   * values values are: BIG, SMALL, MEDIUM
   */
  private String type;

  public List<Footi> getFootis() {
    return footis;
  }

  public void setFootis(List<Footi> footis) {
    this.footis = footis;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
