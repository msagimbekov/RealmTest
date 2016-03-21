package com.realmtest.helper;

/**
 * Created by Madi on 20.03.2016.
 */
public class Counter {

    private int value;
    private static Counter counter;

    /* private, to make counter instance singleton, so that app used only one object of Counter class */
    private Counter() {
        value = 0;
    }

    public static Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }


    /*setter, getter and incrementValue methods are synchronized, so that only one thread can change/read the value of counter at a time*/
    public synchronized void setValue(int value) {
        this.value = value;
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void incrementValue() {
        value++;
    }

}
