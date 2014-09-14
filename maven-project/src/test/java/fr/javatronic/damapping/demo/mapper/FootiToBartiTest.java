package fr.javatronic.damapping.demo.mapper;

import fr.javatronic.damapping.demo.bean.Footi;

import java.math.BigDecimal;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static fr.javatronic.damapping.demo.mapper.FootiToBarti.INSTANCE;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * FootiToBartiTest -
 *
 * @author Sébastien Lesaint
 */
public class FootiToBartiTest {
  @Test
  public void apply_returns_null_for_null_parameter() throws Exception {
    assertThat(INSTANCE.apply(null)).isNull();
  }

  @Test
  public void apply_returns_Barti_with_size_ZERO_when_Footi_has_null_size() throws Exception {
    Footi footi = new Footi();
    assertThat(INSTANCE.apply(footi).getSize()).isEqualTo(BigDecimal.ZERO);
  }

  @Test(dataProvider = "apply_returns_name_of_Footi_as_name_of_Barti_DP")
  public void apply_returns_name_of_Footi_as_name_of_Barti(String name) throws Exception {
    Footi res = new Footi();
    res.setName(name);
    assertThat(INSTANCE.apply(res).getName()).isEqualTo(name);
  }

  @DataProvider
  public Object[][] apply_returns_name_of_Footi_as_name_of_Barti_DP() {
    return new Object[][] {
        { null },
        { "" },
        { "name" },
    };
  }

  @Test
  public void apply_returns_size_of_Footi_as_size_of_Barti_if_not_null() throws Exception {
    Footi res = new Footi();
    res.setSize(5);
    assertThat(INSTANCE.apply(res).getSize()).isEqualTo(new BigDecimal(5));
  }

}