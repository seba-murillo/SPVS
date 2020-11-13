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
}