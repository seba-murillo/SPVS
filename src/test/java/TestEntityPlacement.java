import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Screen;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class TestEntityPlacement {

    @BeforeEach
    void setup(){
        State.initialize(10, 10, 1000);
        Screen.create(10, 10);
    }

    @Test
    void test_invalid_placement() {
        State state = State.getCurrent();
        assertFalse(state.addEntity(new Rabbit(), -5, -9));
        assertFalse(state.addEntity(new Plant(), -5, 2));
        assertFalse(state.addEntity(new Stone(), 1, 999));
        assertFalse(state.addEntity(new Rabbit(), 999, 999));
    }

    @Test
    void test_valid_placement() {
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
        State state = State.getCurrent();
        assertTrue(state.addEntity(new Rabbit(), 0, 0));
        assertFalse(state.addEntity(new Rabbit(), 0, 0));
        assertFalse(state.addEntity(new Stone(), 0, 0));
    }
}