package fr.javatronic.damapping.demo.moduleb;

import fr.javatronic.damapping.annotation.Mapper;

import javax.annotation.Nullable;
import com.google.common.base.Function;

/**
 * StringToInteger -
 *
 * @author Sébastien Lesaint
 */
@Mapper
public class StringToInteger implements Function<String, Integer> {

  @Nullable
  @Override
  public Integer apply(@Nullable String s) {
    // implementation doesn't matter in sample project
    return 0;
  }
}
