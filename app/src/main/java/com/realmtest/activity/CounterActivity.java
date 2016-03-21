package com.realmtest.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.realmtest.helper.Counter;
import com.realmtest.R;
import com.realmtest.app.RealmTestApplication;
import com.realmtest.helper.Viewable;
import com.realmtest.thread.AutoCounter;
import com.realmtest.util.AppUtil;

public class CounterActivity extends Activity {

    private TextView counterValueTextView;
    private Counter counter;
    private AutoCounter autoCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();

        /* getting saved state when resumed if counter was autoincrementing or manually incremeting*/
        RealmTestApplication.AUTO_INCREMENT = AppUtil.getBooleanPreference(this, RealmTestApplication.AUTO_INCREMENT_VALUE, RealmTestApplication.AUTO_INCREMENT_DEFAULT_VALUE);

        if (RealmTestApplication.AUTO_INCREMENT) {
            createAutoIncrementing(); // if counter was autoincrementing, then continue doing that
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        cancelAutoIncrementing(); //try cancelling autoincrement
    }

    @Override
    protected void onStop() {
        super.onStop();

        /* save the last value of counter*/
        AppUtil.setIntPreference(this, RealmTestApplication.COUNTER_VALUE, counter.getValue());

        /* save whether it was autoincrementing or not*/
        AppUtil.setBooleanPreference(this, RealmTestApplication.AUTO_INCREMENT_VALUE, RealmTestApplication.AUTO_INCREMENT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void tryIncrementing(View v) {

        /* check if thread is created to autoincrement counter*/
        if (!RealmTestApplication.AUTO_INCREMENT) { // if thread tasks cancelled, then increment manually
            counter.incrementValue();
            showCounterValue();
        }
    }

    public void autoIncrement(View v) {

        RealmTestApplication.AUTO_INCREMENT = !RealmTestApplication.AUTO_INCREMENT; //toggle state, which is used to create thread or cancel thread tasks
        if (RealmTestApplication.AUTO_INCREMENT) { //if flag is to create thread, create one
            createAutoIncrementing();
        } else { // if thread is already created, then cancel tasks of thread
            cancelAutoIncrementing();
        }

    }

    private synchronized void showCounterValue() {
        counterValueTextView.setText(String.valueOf(counter.getValue()));
    }

    private void init() {
        counterValueTextView = (TextView) findViewById(R.id.counterValueTextView);
        counter = Counter.getInstance();
        showCounterValue();
    }

    private void cancelAutoIncrementing() {
        if (autoCounter != null) {
            autoCounter.cancel();
        }
    }

    private void createAutoIncrementing() {
        autoCounter = new AutoCounter(counter, new Viewable() {
            @Override
            public void view() {
                showCounterValue();
            }
        });
        /*Since we can not manually stop the thread, we run them in thread pool, but we could cancel tasks done by previously run threads */
        autoCounter.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

}
