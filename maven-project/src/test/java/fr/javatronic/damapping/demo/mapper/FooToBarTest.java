package fr.javatronic.damapping.demo.mapper;

import fr.javatronic.damapping.demo.bean.Bar;
import fr.javatronic.damapping.demo.bean.Foo;
import fr.javatronic.damapping.demo.bean.Footi;
import fr.javatronic.damapping.demo.bean.Type;

import java.util.Collections;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static fr.javatronic.damapping.demo.bean.Type.SMALL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * FooToBarTest -
 *
 * @author Sébastien Lesaint
 */
public class FooToBarTest {

  @Mock
  private FootiToBartiMapper footiToBartiMapper;
  @InjectMocks
  private FooToBar underTest;

  @BeforeMethod
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void transform_returns_small_Bar_with_empty_list_of_Bartis_if_argument_is_null() throws Exception {
    Bar bar = underTest.transform(null);
    assertThat(bar).isNotNull();
    assertThat(bar.getType()).isEqualTo(SMALL);
    assertThat(bar.getBartis()).isEmpty();
  }

  @Test
  public void transform_returns_SMALL_type_when_Foo_type_is_null() throws Exception {
    assertThat(underTest.transform(new Foo()).getType()).isEqualTo(SMALL);
  }

  @Test
  public void transform_returns_type_when_the_value_of_Foo_type_is_exactly_the_name_of_the_enum() throws Exception {
    for (Type type : Type.values()) {
      Foo foo = new Foo();
      foo.setType(type.name());

      assertThat(underTest.transform(foo).getType())
          .describedAs("Footi.type=%s is mapped to enum value Type.%s", type.name(), type)
          .isEqualTo(type);
    }
  }

  @Test(dataProvider = "invalide_type_values_DP")
  public void transform_returns_SMALL_type_when_the_value_of_Foo_type_is_not_exactly_the_name_of_the_enum(String typeValue) throws Exception {
    for (Type type : Type.values()) {
      Foo foo = new Foo();
      foo.setType(typeValue);

      assertThat(underTest.transform(foo).getType()).isEqualTo(SMALL);
    }
  }

  @DataProvider
  public Object[][] invalide_type_values_DP() {
    return new Object[][] {
        { "sdqsd" },
        { "BiG"},
        { "big"},
        { "SMAlL"}
    };
  }

  @Test
  public void transform_returns_empty_list_of_Barti_when_Foo_has_null_list_of_Footi() throws Exception {
    assertThat(underTest.transform(new Foo()).getBartis()).isEmpty();
  }

  @Test
  public void transform_returns_empty_list_of_Barti_when_Foo_has_empty_list_of_Footi() throws Exception {
    Foo foo = new Foo();
    foo.setFootis(Collections.<Footi>emptyList());

    assertThat(underTest.transform(foo).getBartis()).isEmpty();

    verifyZeroInteractions(footiToBartiMapper);
  }

  @Test
  public void transform_filters_out_null_Barti_returned_by_FootiToBartiMapper() throws Exception {
    Foo foo = new Foo();
    Footi footi = new Footi();
    foo.setFootis(Collections.singletonList(footi));
    when(footiToBartiMapper.apply(footi)).thenReturn(null);

    assertThat(underTest.transform(foo).getBartis()).isEmpty();

    // makes sure mapper is called only once
    verify(footiToBartiMapper).apply(footi);
    verifyNoMoreInteractions(footiToBartiMapper);
  }
}
