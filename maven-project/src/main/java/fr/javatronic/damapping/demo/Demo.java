package fr.javatronic.damapping.demo;

/**
 * Demo -
 *
 * @author Sébastien Lesaint
 */
public class Demo {
  public static void main(String[] args) {
    StringToIntegerMapper mapper = new StringToIntegerMapperImpl();

    assert mapper.apply("some_string") == 0;
  }
}
