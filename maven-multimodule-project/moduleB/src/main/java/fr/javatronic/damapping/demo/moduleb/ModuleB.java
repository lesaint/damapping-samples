package fr.javatronic.damapping.demo.moduleb;

/**
 * ModuleB -
 *
 * @author Sébastien Lesaint
 */
public class ModuleB {
  public static void main(String[] args) {

    // StringToIntegerMapper and StringToIntegerMapperImpl classes can not be generated in this module
    // because DAMapping annotation processor is not declared as a compile dependency
    StringToInteger mapper = new StringToInteger();

    assert mapper.apply("somestring") == 0;
  }
}
