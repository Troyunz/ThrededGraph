package com.example.thrededgraph;

import android.os.Looper;

public class GraphThread extends Thread {

    public GraphHandler mhandler;
    public final MainActivity mActivity;

    public GraphThread(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void run() {

        Looper.prepare();
        mhandler = new GraphHandler(mActivity);
        Looper.loop();

    }
}
