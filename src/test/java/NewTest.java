import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class NewTest {
    private static Game game;

    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String format, Object ... args){
        System.out.println(String.format(format, args));
    }


    @Test
    void test(){
        Game.start(10, 10, 100);
        State state = State.getCurrent();
        state.addEntity(new Plant(), 0, 0);
        state.addEntity(new Rabbit(), 9, 9);
        while(true){
            delay();
        }
    }

    @Test
    void clonetest(){
        Game.start(10, 10, 100);
        State state1 = State.getCurrent();
        State state2 = (State) state1.copy();
        if (state1 == state2) {
            log("same ref");
        } else {
            log("diff ref");
        }
        state2.addEntity(new Plant(), 0, 0);
        state2.addEntity(new Plant(), 1, 1);
        state2.addEntity(new Plant(), 2, 2);
        for(int i = 0; i < 20;i++){
            if(i % 2 == 0){
                State.set(state1);
            }else{
                State.set(state2);
            }
            delay();
        }

    }

    @Test
    void test_fluffy_position() {
        int start_pos = 4;
        //
        State state = State.getCurrent();
        Fluffy fluffy = new Fluffy();
        state.addEntity(fluffy, start_pos, start_pos);
        for (int i = 0; i < 8; i++) {
            State.next();
            delay();
        }
        assertEquals(fluffy.getX(), start_pos);
        assertEquals(fluffy.getY(), start_pos);
    }

    @Test
    void test_fluffy_attack() {
        int start_pos = 4;
        //Game.start(2 * start_pos + 1, 2 * start_pos + 1, -1);
        State state = State.getCurrent();
        Fluffy fluffy = new Fluffy();
        FixedRabbit R1 = new FixedRabbit();
        FixedRabbit R2 = new FixedRabbit();
        FixedRabbit R3 = new FixedRabbit();
        state.addEntity(fluffy, start_pos, start_pos);
        state.addEntity(R1, start_pos - 2, start_pos - 1);
        state.addEntity(R2, start_pos, start_pos - 3);
        state.addEntity(R3, start_pos + 2, start_pos - 1);
        for (int i = 0; i < 8; i++) {
            State.next();
            delay();
        }
        assertEquals(fluffy.getX(), start_pos);
        assertEquals(fluffy.getY(), start_pos);
        assertFalse(R1.isAlive());
        assertFalse(R2.isAlive());
        assertFalse(R3.isAlive());
    }
}
