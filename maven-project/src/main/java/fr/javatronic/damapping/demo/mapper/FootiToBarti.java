package fr.javatronic.damapping.demo.mapper;

import fr.javatronic.damapping.annotation.Mapper;
import fr.javatronic.damapping.demo.bean.Footi;
import fr.javatronic.damapping.demo.bean.Barti;

import java.math.BigDecimal;
import javax.annotation.Nullable;
import com.google.common.base.Function;

@Mapper
public enum FootiToBarti implements Function<Footi, Barti> {
  INSTANCE;

  private static final BigDecimal DEFAULT_SIZE = BigDecimal.ZERO;

  @Nullable
  @Override
  public Barti apply(@Nullable Footi input) {
    if (input == null) {
      return null;
    }
    BigDecimal size = input.getSize() == null ? DEFAULT_SIZE : new BigDecimal(input.getSize());
    return new Barti(input.getName(), size);
  }
}
