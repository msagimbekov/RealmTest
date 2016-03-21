package com.realmtest.thread;

import android.os.AsyncTask;
import android.os.Handler;

import com.realmtest.helper.Counter;
import com.realmtest.helper.Viewable;

/**
 * Created by Madi on 21.03.2016.
 */
public class AutoCounter extends AsyncTask<Void, Void, Void> {

    private Counter counter;
    private Viewable viewable;
    private Handler handler;
    private Runnable increment;

    public AutoCounter(Counter c, Viewable v) {
        this.counter = c;
        this.viewable = v;
        handler = new Handler();
        increment = new Runnable() {
            @Override
            public void run() {
                counter.incrementValue();
                viewable.view();
                handler.postDelayed(this, 1000); // call itself to increment counter value by 1 after one second
            }
        };
    }

    @Override
    protected Void doInBackground(Void... params) {

        /*when created thread and executed, set timer to 1s to increment counter value*/
        handler.postDelayed(increment, 1000);
        return null;
    }

    public void cancel() {
        /*cancel inner job which is responsible to increment counter value every second*/
        handler.removeCallbacks(increment);
    }


}
