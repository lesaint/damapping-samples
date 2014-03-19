package fr.javatronic.damapping.demo.modulea;

/**
 * ModuleA -
 *
 * @author Sébastien Lesaint
 */
public class ModuleA {
  public static void main(String[] args) {
    StringToIntegerMapper mapper = new StringToIntegerMapperImpl();

    assert mapper.apply("some_string") == 0;
  }
}
