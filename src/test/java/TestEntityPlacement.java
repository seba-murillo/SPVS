import controller.Controller;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntityPlacement {
/*
    @BeforeEach //@Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Game.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }*/

    @Test
    void test_invalid_placement() {
        Game.start(10, 10, 999);
        State state = State.getCurrent();
        assertFalse(state.addEntity(new Rabbit(), -5, -9));
        assertFalse(state.addEntity(new Plant(), -5, 2));
        assertFalse(state.addEntity(new Stone(), 1, 999));
        assertFalse(state.addEntity(new Rabbit(), 999, 999));
    }

    @Test
    void test_valid_placement() {
        Game.start(10, 10, 999);
        State state = State.getCurrent();
        assertTrue(state.addEntity(new Plant(), 0, 0));
        assertTrue(state.addEntity(new Tree(), 1, 1));
        assertTrue(state.addEntity(new Stone(), 2, 2));
        assertTrue(state.addEntity(new Rabbit(), 3, 3));
        assertTrue(state.addEntity(new Wolf(), 4, 4));
        assertTrue(state.addEntity(new Bear(), 5, 5));
    }

    @Test
    void test_duplicate_placement() {
        Game.start(10, 10, 999);
        State state = State.getCurrent();
        assertTrue(state.addEntity(new Rabbit(), 0, 0));
        assertFalse(state.addEntity(new Rabbit(), 0, 0));
        assertFalse(state.addEntity(new Stone(), 0, 0));
    }

    @Test
    void test(){
        int start_pos = 3;
        Game.start(2 * start_pos + 1, 2 * start_pos + 1, 999);
        State state = State.getCurrent();
        Fluffy fluffy = new Fluffy();
        state.addEntity(fluffy, start_pos, start_pos);
        Entity P1 = new Plant();
        Entity P2 = new Plant();
        Entity S1 = new Stone();
        Entity S2 = new Stone();
        Entity T1 = new Tree();
        Entity T2 = new Tree();
        Entity R1 = new FixedRabbit();
        Entity R2 = new FixedRabbit();
        state.addEntity(P1, start_pos + 0, start_pos - 1);
        state.addEntity(P2, start_pos + 0, start_pos + 1);
        state.addEntity(S1, start_pos - 1, start_pos + 0);
        state.addEntity(S2, start_pos + 1, start_pos + 0);
        state.addEntity(T1, start_pos - 1, start_pos + 1);
        state.addEntity(T2, start_pos + 1, start_pos + 1);
        state.addEntity(R1, start_pos - 1, start_pos - 1);
        state.addEntity(R2, start_pos + 1, start_pos - 1);
        Entity[][] surroundings = new Entity[0][];
        //Entity[][] surroundings = Controller.getCurrentState().getSurroundings(fluffy.getX(), fluffy.getY());
        //fluffy.getSurroundings();
        assertEquals(surroundings[0][0], R1);
        assertEquals(surroundings[0][1], S1);
        assertEquals(surroundings[0][2], T1);
        assertEquals(surroundings[1][0], P1);
        assertEquals(surroundings[1][1], null);
        assertEquals(surroundings[1][2], P2);
        assertEquals(surroundings[2][0], R2);
        assertEquals(surroundings[2][1], S2);
        assertEquals(surroundings[2][2], T2);
    }
}