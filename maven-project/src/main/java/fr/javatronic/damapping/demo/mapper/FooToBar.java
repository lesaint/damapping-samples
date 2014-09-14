package fr.javatronic.damapping.demo.mapper;

import fr.javatronic.damapping.annotation.Mapper;
import fr.javatronic.damapping.demo.bean.Bar;
import fr.javatronic.damapping.demo.bean.Barti;
import fr.javatronic.damapping.demo.bean.Foo;
import fr.javatronic.damapping.demo.bean.Footi;
import fr.javatronic.damapping.demo.bean.Type;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.FluentIterable.from;

/**
 * This mapper demonstrates:
 * <ul>
 * <li>the convenience of creating mapper which implements {@link com.google.common.base.Function} to transform
 * collections (see {@link FootiToBarti}</li>
 * <li>how convenient having the hand on mapping code is to write mapping code with specific requirements. here we
 * present: very protective code and default values</li>
 * <li>how we decide to use Guava's {@link com.google.common.collect.FluentIterable} to map the List property of source
 * type {@link Foo#footis} to the list property {@link Bar#bartis}</li>
 * </ul>
 */
@Mapper
public class FooToBar {
  private static final Bar DEFAULT_BAR = new Bar(Type.SMALL, null);
  private static final Type DEFAULT_TYPE = Type.SMALL;

  private final FootiToBartiMapper footiToBartiMapper;

  public FooToBar(FootiToBartiMapper footiToBartiMapper) {
    this.footiToBartiMapper = footiToBartiMapper;
  }

  @Nonnull
  public Bar transform(@Nullable Foo foo) {
    // this is a specific business rule: when Foo is null, a default Bar is returned
    if (foo == null) {
      return DEFAULT_BAR;
    }

    // Foo instance does not enforce non null values and the object returned by the service producing Foo instance
    // is known to be unreliable in the object it sends out. So we need to write code as protective as possible;
    //  - null check and use default values
    //  - make controls over returned values
    return new Bar(
        convertType(foo.getType()),
        convertFootis(foo.getFootis())
    );
  }

  @Nullable
  private List<Barti> convertFootis(@Nullable List<Footi> footis) {
    if (footis == null || footis.isEmpty()) {
      return Collections.emptyList();
    }

    return from(footis)
        // filter out any null in source list
        .filter(notNull())
        .transform(footiToBartiMapper)
            // filter out any null value returned by the mapper (return value is annotated with @Nullable)
        .filter(notNull())
        .toList();
  }

  @Nonnull
  private Type convertType(@Nullable String type) {
    if (type == null || type.isEmpty()) {
      return DEFAULT_TYPE;
    }
    try {
      return Type.valueOf(type);
    } catch (IllegalArgumentException e) {
      // we should log the error here, but we will just return a default value for demonstration
      return DEFAULT_TYPE;
    }
  }
}
