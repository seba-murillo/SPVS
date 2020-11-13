package model;

import view.Screen;

import java.util.*;


public class State implements Cloneable {

    public static final int MAX_MOVE_ATTEMPT = 10;

    public static int initialDuration = 0;

    public static boolean done = false;

    private static State current = null;
    private static int width = 0;
    private static int height = 0;

    private static final TreeMap<Integer, State> states = new TreeMap<>();
    private static final HashSet<Observer> observers = new HashSet<>();
    private static int ID = 0;

    private final List<Entity> entities = new ArrayList<>();

    private final Entity[][] grid = new Entity[State.width][State.height];
    private final int duration;

    private State(int duration) {
        this.duration = duration;
    }

    public static void initialize(int width, int height, int duration) {
        initialDuration = duration;
        State.width = width;
        State.height = height;
        current = new State(duration);
    }

    public static int getWidth() {
        return State.width;
    }

    public static int getHeight() {
        return State.height;
    }

    public static Entity[][] getGrid() {
        return current.grid;
    }

    public static List<Entity> getEntities() {
        return current.entities;
    }

    public boolean addEntity(Entity entity, int x, int y) {
        if (!setEntityPos(entity, x, y)) return false;
        entities.add(entity);
        return true;
    }

    private boolean setEntityPos(Entity entity, int x, int y) {
        if (x < 0 || x >= State.width) return false;
        if (y < 0 || y >= State.height) return false;
        if (grid[x][y] != null) {
            return (grid[x][y] == entity);
            //log("@State - cannot place [" + entity + "] at (" + x + ", " + y + "): space already in use by " + grid[x][y].toString());
        }
        int prev_x = entity.getX();
        int prev_y = entity.getY();
        // TODO FIX invert grid coords
        if (prev_x != Entity.INEX && prev_y != Entity.INEX) grid[prev_x][prev_y] = null; // remove from previous pos
        grid[x][y] = entity;
        entity.setP(x, y);
        updateObservers();
        return true;
    }

    /*       -
     * 	   -[1] [4] [7]+
     * 		[2] [0] [6]
     * 		[3] [8] [5]
     *       +
     */
    private boolean setEntityPos(Entity entity, int dir) {
        switch (dir) {
            case 0: {
                return true;
            }
            case 1: // 1 = 9
            case 9: {
                return (setEntityPos(entity, entity.getX() - 1, entity.getY() - 1));
            }
            case 2: {
                return (setEntityPos(entity, entity.getX(), entity.getY() - 1));
            }
            case 3: {
                return (setEntityPos(entity, entity.getX() + 1, entity.getY() - 1));
            }
            case 4: {
                return (setEntityPos(entity, entity.getX() + 1, entity.getY()));
            }
            case 5: {
                return (setEntityPos(entity, entity.getX() + 1, entity.getY() + 1));
            }
            case 6: {
                return (setEntityPos(entity, entity.getX(), entity.getY() + 1));
            }
            case 7: {
                return (setEntityPos(entity, entity.getX() - 1, entity.getY() + 1));
            }
            case 8: {
                return (setEntityPos(entity, entity.getX() - 1, entity.getY()));
            }
            default: {
                return false;
            }
        }
    }

    public int[] getClosestEntityType(int type, int x, int y) {
        int[] pos = {-1, -1};
        double dist;
        double min_dist = 999;
        for (Entity ent : entities) {
            if (ent.getType() != type) continue;
            if (!ent.isAlive()) continue;
            dist = Math.sqrt(Math.pow(ent.getX() - x, 2) + Math.pow(ent.getY() - y, 2));
            if (dist < min_dist) {
                min_dist = dist;
                pos[0] = ent.getX();
                pos[1] = ent.getY();
            }
        }
        return pos;
    }

    public Entity[][] getSurroundings(int pos_x, int pos_y) {
        //todo refactor
        Entity[][] surroundings = new Entity[3][3];
        int start_x = pos_x - 1; // 2
        int start_y = pos_y - 1; // 2
        for (int x = start_x; x <= (pos_x + 1); x++) { // 2 -> 4
            if (x < 0 || x >= State.width) continue;
            for (int y = start_y; y <= (pos_y + 1); y++) {// 2 -> 4
                if (y < 0 || y >= State.height) continue;
                if (pos_x == x && pos_y == y) continue;
                if (x - start_x < 0 || x - start_x >= State.width) continue;
                if (y - start_y < 0 || y - start_y >= State.height) continue;
                surroundings[x - start_x][y - start_y] = grid[x][y];
            }
        }
        return surroundings;
    }

    public static State getCurrent() {
        return current;
    }
    /*
    ===========================================================
    =   miscellaneous methods
    ===========================================================
    */

    @Override
    public String toString() {
        return String.format("State [%d]", ID);
    }

    private static void log(String format, Object... args) {
        System.out.printf((format) + "%n", args);
    }
    /*
    ===========================================================
    =   state change
    ===========================================================
    */

    private void tick() {
        if (duration < 1) {
            Screen.showEndScreen(initialDuration);
            return;
        }
        //log("- ticking state %d", ID);
        save();
        ID++;
        //log("- clonning entities");
        for (int e = 0; e < entities.size(); e++) {// clone entities
            Entity OLD = entities.get(e);
            Entity NEW = OLD.copy();
            entities.remove(OLD);
            entities.add(e, NEW);
        }
        //log("- moving new entities");
        for (Entity entity : entities) {
            for (int attempt = 0; attempt < MAX_MOVE_ATTEMPT; attempt++) {
                if (setEntityPos(entity, entity.move())) {
                    break; // continue outer
                }
            }
        }
        updateObservers();
    }

    private void save() {
        if (ID == 3) printall();
        //log("- saving state %d", ID);
        try {
            states.put(ID, (State) this.clone());
        } catch (CloneNotSupportedException e) {
            log("ERROR @ State.save() : failed clonning");
        }
        //log("- state saved");
    }

    private static State load(int stateID) {
        if (stateID < 0) stateID = 0;
        log("- loading state %d of %d", ID, states.size());
        ID = stateID;
        State state = states.get(ID);
        state.print();
        return state;
    }
/*
    public static void loadState(int ID) {
        current = State.load(ID);
        updateObservers();
    }*/

    public static void prev() {
        current = State.load(ID - 1);
        updateObservers();
    }

    public static void next() {
        current.tick();
    }

    public Entity getEntityAt(int x, int y) {
        return grid[x][y];
    }

    public void removeEntityAt(int x, int y) {
        entities.remove(grid[x][y]);
        grid[x][y] = null;
        updateObservers();
    }

    /*
    ===========================================================
    =   subject interface methods
    ===========================================================
    */
    public static void updateObservers() {
        Screen.updateTitle(ID);
        for (Observer obs : observers)
            obs.update();
    }

    public static void register(Observer observer) {
        observers.add(observer);
    }

    @SuppressWarnings("unused")
    public static void unregister(Observer observer) {
        observers.remove(observer);
    }

    /*
    ===========================================================
    =   test & debug functions
    ===========================================================
    */

    public void print() {
        log("> %s:", this.toString());
        for (Entity entity : entities) {
            log("    %s [%d]: (%d,%d)", entity.toString(), entity.hashCode(), entity.getX(), entity.getX());
        }
        log("\n");
    }

    public static void printall() {
        log("PRINTING ALL");
        states.forEach((key, value) -> value.print());
        log("\n");
        log("PRINTED ALL");
    }

    public static void set(State newstate) {
        current = newstate;
        updateObservers();
    }

    public State copy() {
        try {
            return (State) current.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        log("FK FK FK");
        return null;
    }

}
