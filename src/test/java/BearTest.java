import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import controller.Controller;
import model.Game;
import model.Plant;
import model.Rabbit;
import model.State;
import model.Bear;

public class BearTest {
    /*
    Bear bear = new Bear("bearUnderTest");
    @BeforeEach
    public void setUp(){
        bear = new Bear("bearUnderTest");
    }*/

    @Test
    public void Given_DeadBear_When_MoveIsRequested_Then_KeepsPosition(){
        //Given
        Bear bear = new Bear("bearUnderTest");
        //When
        bear.kill(bear.toString() + " was eaten by " + "Furious Rabbit");
        //Then
        Assertions.assertFalse(bear.isAlive());
    }
}
