package fr.javatronic.damapping.demo.bean;

import java.math.BigDecimal;

public class Barti {
  private final String name;
  private final BigDecimal size;

  public Barti(String name, BigDecimal size) {
    this.name = name;
    this.size = size;
  }

  public String getName() {
    return name;
  }

  public BigDecimal getSize() {
    return size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Barti barti = (Barti) o;

    if (!name.equals(barti.name)) {
      return false;
    }
    if (!size.equals(barti.size)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + size.hashCode();
    return result;
  }
}
