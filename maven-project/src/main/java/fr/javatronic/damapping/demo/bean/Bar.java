package fr.javatronic.damapping.demo.bean;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Bar - demonstrates a fully immutable bean that enforces non null properties and uses annotations
 * {@link Immutable}, {@link @Nonnull} and {@linkn Nullable} annotations to document it.
 */
@Immutable
public class Bar {
  @Nonnull
  private final List<Barti> bartis;
  @Nonnull
  private final Type type;

  public Bar(@Nonnull Type type, @Nullable List<Barti> bartis) {
    this.type = checkNotNull(type);
    this.bartis = bartis == null ? Collections.<Barti>emptyList() : ImmutableList.copyOf(bartis);
  }

  @Nonnull
  public List<Barti> getBartis() {
    return bartis;
  }

  @Nonnull
  public Type getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Bar bar = (Bar) o;

    if (!bartis.equals(bar.bartis)) {
      return false;
    }
    if (type != bar.type) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = bartis.hashCode();
    result = 31 * result + type.hashCode();
    return result;
  }
}