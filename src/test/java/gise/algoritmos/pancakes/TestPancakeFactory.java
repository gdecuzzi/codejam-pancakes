package gise.algoritmos.pancakes;

import static org.assertj.core.api.Assertions.*;

import gise.algoritmos.pancakes.NoHappyAllowed;
import gise.algoritmos.pancakes.PancakesFactory;
import org.junit.Before;
import org.junit.Test;

public class TestPancakeFactory {

  private PancakesFactory factory;

  @Before
  public void setUp(){
    factory = new PancakesFactory();
  }


  @Test
  public void allHappyCanFlip(){
    assertThat(factory.beHappy("+++++",4)).isEqualTo(0);
  }

  @Test
  public void withAMixWeCanFlip(){
    assertThat(factory.beHappy("---+-++-",3)).isEqualTo(3);
  }

  @Test
  public void withAMoreMixWeCanFlip(){
    assertThat(factory.beHappy("++--+-+--",3)).isEqualTo(3);
  }




  @Test
  public void oneAndOneCannotFlip(){
    try {
      factory.beHappy("+-+-", 4);
      failBecauseExceptionWasNotThrown(NoHappyAllowed.class);
    }catch (NoHappyAllowed e){
      assertThat(factory.allHappy("+-+-",3)).isEqualTo("IMPOSSIBLE");
    }
  }

  @Test
  public void notArrangable(){
    try {
      factory.beHappy("-+++-", 3);
      failBecauseExceptionWasNotThrown(NoHappyAllowed.class);
    }catch (NoHappyAllowed e){
      assertThat(factory.allHappy("+-+-",3)).isEqualTo("IMPOSSIBLE");
    }
  }


  @Test
  public void more(){
    assertThat(factory.beHappy("++--+-+--",3)).isEqualTo(3);
  }

  @Test
  public void theHeavyOne(){
    assertThat(factory.beHappy("+-+-+-+",3)).isEqualTo(3);
  }


}
