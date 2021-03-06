package fr.javatronic.damapping.demo;

import fr.javatronic.damapping.demo.bean.Bar;
import fr.javatronic.damapping.demo.bean.Foo;
import fr.javatronic.damapping.demo.bean.Footi;
import fr.javatronic.damapping.demo.mapper.FooToBarMapper;
import fr.javatronic.damapping.demo.mapper.FooToBarMapperImpl;
import fr.javatronic.damapping.demo.mapper.FootiToBartiMapper;
import fr.javatronic.damapping.demo.mapper.FootiToBartiMapperImpl;

import java.util.List;
import com.google.common.collect.Lists;

/**
 * Demo -
 *
 * @author Sébastien Lesaint
 */
public class Demo {

  /**
   * main method.
   *
   * Instanciates the classes generated by DAMapping, creates a {@link Foo} instance, transforms it into a
   * {@link Bar} object using the {@link FooToBarMapper} class and prints the source and mapped objects to standard
   * output.
   */
  public static void main(String[] args) {
    // instance mappers, wiring manually
    FootiToBartiMapper footiToBartiMapper = new FootiToBartiMapperImpl();
    FooToBarMapper fooToBarMapper = new FooToBarMapperImpl(footiToBartiMapper);

    Foo foo = newFoo("MEDIUM", Lists.newArrayList(footi("nla", null), null, footi("a", 10)));
    Bar bar = fooToBarMapper.transform(foo);
    System.out.println(String.format("%s -> %s", foo, bar));
  }

  /**
   * static convenience method to instanciate a Footi instance.
   */
  private static Footi footi(String name, Integer size) {
    Footi res = new Footi();
    res.setName(name);
    res.setSize(size);
    return res;
  }

  /**
   * static convenience method to instanciate a Foo instance.
   */
  private static Foo newFoo(String type, List<Footi> footis) {
    Foo foo = new Foo();
    foo.setType(type);
    foo.setFootis(footis);
    return foo;
  }
}
