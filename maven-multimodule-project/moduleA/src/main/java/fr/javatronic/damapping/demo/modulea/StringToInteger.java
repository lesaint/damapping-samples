package fr.javatronic.damapping.demo.modulea;

import javax.annotation.Nullable;
import com.google.common.base.Function;

import fr.javatronic.damapping.annotation.Mapper;

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
