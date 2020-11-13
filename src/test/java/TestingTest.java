import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestingTest {

    private void delay(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_rabbit_vs_plants() {
        Game.start(10,10,100);
        State state = State.getCurrent();
        Rabbit rabbit = new Rabbit();
        Plant P1 = new Plant();
        Plant P2 = new Plant();
        Plant P3 = new Plant();
        Plant P4 = new Plant();
        state.addEntity(rabbit, 4, 4);
        //state.addEntity(P1, 0, 0);
        state.addEntity(P2, 9, 0);
        state.addEntity(P3, 9, 9);
        state.addEntity(P4, 0, 9);
        for (int i = 0; i < 50; i++) {
            State.next();
            State.updateObservers();
            delay();
        }
        assertTrue(P1.getTimesEaten() > 0);
        assertTrue(P2.getTimesEaten() > 0);
        assertTrue(P3.getTimesEaten() > 0);
        assertTrue(P4.getTimesEaten() > 0);
    }

    @Test
    void test_wolf_vs_rabbits() {
        Game.start(10,10,100);
        State state = State.getCurrent();
        Rabbit[] R = new Rabbit[4];
        for (int i = 0; i < 4; i++) {
            R[i] = new Rabbit();
        }
        state.addEntity(R[0], 4, 4);
        state.addEntity(R[1], 7, 2);
        state.addEntity(R[2], 2, 7);
        state.addEntity(R[3], 7, 7);
        state.addEntity(new Wolf(), 8, 8);
        for (int i = 0; i < 50; i++) {
            State.next();
        }
        for (int i = 0; i < 4; i++) {
            assertFalse(R[i].isAlive());
        }
    }
}
