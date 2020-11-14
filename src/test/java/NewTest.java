import model.*;
import org.junit.jupiter.api.Test;


public class NewTest {
    private void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void log(String format, Object ... args){
        System.out.printf((format) + "%n", args);
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
    void clonetest() throws CloneNotSupportedException{
        Entity e1 = new Wolf("wolfie");

        Entity e2 = e1.copy();e1.setFood(99);
        if(e1 == e2){
            log("e1 == e2");
        }
        if(e1.equals(e2)){
            log("e1.equals(e2)");
        }
        log("e1: " + e1);
        log("e2: " + e2);
        log("fff1: " + e1.getFood());
        log("fff2: " + e2.getFood());
    }

}
