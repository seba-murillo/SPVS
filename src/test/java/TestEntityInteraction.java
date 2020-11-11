import controller.Controller;
import model.*;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntityInteraction {


    @BeforeEach
    void reset() {
        Game.start(10, 10, -1);
    }

    @Test
    void test_fluffy_movement() {
        Fluffy fluffy = new Fluffy();
        for (int pos = 2; pos < 9; pos += 2) {
            assertEquals(fluffy.move(), pos);
        }
        for (int pos = 1; pos < 9; pos += 2) {
            assertEquals(fluffy.move(), pos);
        }
    }

    /*       -
     * 	   -[1] [4] [7]+
     * 		[2] [0] [6]
     * 		[3] [8] [5]
     *       +
     */
    @Test
    void test_fluffy_position() {
        int start_pos = 4;
        //Game.start(2 * start_pos + 1, 2 * start_pos + 1, -1);
        State state = Controller.getCurrentState();
        Fluffy fluffy = new Fluffy();
        state.addEntity(fluffy, start_pos, start_pos);
        for (int i = 0; i < 8; i++) {
            state.tick();
        }
        assertEquals(fluffy.getX(), start_pos);
        assertEquals(fluffy.getY(), start_pos);
    }

    private void delay(int ms){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_fluffy_attack() {
        int start_pos = 4;
        //Game.start(2 * start_pos + 1, 2 * start_pos + 1, -1);
        State state = Controller.getCurrentState();
        Fluffy fluffy = new Fluffy();
        FixedRabbit R1 = new FixedRabbit();
        FixedRabbit R2 = new FixedRabbit();
        FixedRabbit R3 = new FixedRabbit();
        state.addEntity(fluffy, start_pos, start_pos);
        state.addEntity(R1, start_pos - 2, start_pos - 1);
        state.addEntity(R2, start_pos, start_pos - 3);
        state.addEntity(R3, start_pos + 2, start_pos - 1);
        for (int i = 0; i < 8; i++) {
            state.tick();
            State.update_observers();
        }
        assertEquals(fluffy.getX(), start_pos);
        assertEquals(fluffy.getY(), start_pos);
        assertFalse(R1.isAlive());
        assertFalse(R2.isAlive());
        assertFalse(R3.isAlive());
    }

    @Test
    void test_pathfinding() {
        assertEquals(Entity.pathfind(3, 3, 3, 3), 0);
        assertEquals(Entity.pathfind(3, 3, 1, 1), 1);
        assertEquals(Entity.pathfind(3, 3, 3, 1), 2);
        assertEquals(Entity.pathfind(3, 3, 5, 1), 3);
        assertEquals(Entity.pathfind(3, 3, 5, 3), 4);
        assertEquals(Entity.pathfind(3, 3, 1, 3), 8);
        assertEquals(Entity.pathfind(3, 3, 1, 5), 7);
        assertEquals(Entity.pathfind(3, 3, 3, 5), 6);
        assertEquals(Entity.pathfind(3, 3, 5, 5), 5);
    }

    @Test
    void test_rabbit_vs_plants() {
        int start_pos = 4;
        //Game.start(2 * start_pos + 1, 2 * start_pos + 1, -1);
        State state = Controller.getCurrentState();
        Rabbit rabbit = new Rabbit();
        Plant P1 = new Plant();
        Plant P2 = new Plant();
        Plant P3 = new Plant();
        Plant P4 = new Plant();
        state.addEntity(rabbit, start_pos, start_pos);
        state.addEntity(P1, 0, 0);
        state.addEntity(P2, 9, 0);
        state.addEntity(P3, 9, 9);
        state.addEntity(P4, 0, 9);
        for (int i = 0; i < 50; i++) {
            state.tick();
            State.update_observers();
        }
        /*
        assertFalse(P1.isAlive());
        assertFalse(P2.isAlive());
        assertFalse(P3.isAlive());
        assertFalse(P4.isAlive());
         */
    }

    @Test
    void test_wolf_vs_rabbits() {
        //Game.start(10, 10, -1);
        State state = Controller.getCurrentState();
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
            state.tick();
        }
        for (int i = 0; i < 4; i++) {
            assertFalse(R[i].isAlive());
        }
    }

    @Test
    void test_wolf_vs_fixed_rabbits() {
        int start_pos = 4;
        //Game.start(10, 10, -1);
        State state = Controller.getCurrentState();
        Wolf wolf = new Wolf();
        FixedRabbit[] R = new FixedRabbit[4];
        R[0] = new FixedRabbit();
        R[1] = new FixedRabbit();
        R[2] = new FixedRabbit();
        R[3] = new FixedRabbit();
        state.addEntity(wolf, start_pos, start_pos);
        state.addEntity(R[0], 0, 0);
        state.addEntity(R[1], 9, 0);
        state.addEntity(R[2], 9, 9);
        state.addEntity(R[3], 0, 9);
        for (int i = 0; i < 50; i++) {
            state.tick();
            State.update_observers();
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(R[i].isAlive());
        }
    }
}
