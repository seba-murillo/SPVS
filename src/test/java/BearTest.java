import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import model.Bear;

public class BearTest {
    /*
    Bear bear = new Bear("bearUnderTest");
    @BeforeEach
    public void setUp(){
        bear = new Bear("bearUnderTest");
    }*/
    @Test
    public void When_BearHaveBeenEaten_Then_IsDead(){
        //Given
        Bear bear = new Bear("bearUnderTest");
        //When
        bear.kill(bear.toString() + " was eaten by " + "Furious Rabbit");
        //Then
        Assertions.assertFalse(bear.isAlive());
    }

    @Test
    public void Given_DeadBear_WhenMoveIsRequest_ThenReturnZero(){
        //Given
        Bear bear = new Bear("bearUnderTest");
        bear.kill(bear.toString() + " was eaten by " + "Furious Rabbit");
        //When
        //int result = bear.move();
        //Then
        //Assertions.assertEquals(0,result);
    }
}
