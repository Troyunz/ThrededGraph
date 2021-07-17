package com.example.thrededgraph;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

public class GraphHandler extends Handler {

    public final MainActivity mActivity;
    List<Entry> sinEntries = new ArrayList<>();
    List<ILineDataSet> dataSets = new ArrayList<>();
    LineDataSet sinSet;
    float i;

//    ILineDataSet set;

    public GraphHandler(MainActivity mainActivity) {
        this.mActivity = mainActivity;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {


        calculatesin(msg.obj.toString());
    }

    private void calculatesin(String fun)
    {




        for (i = 0; i < 7f; i += 0.02f) {
            sinEntries.add(new Entry(i, (float) Math.sin(i) + 5));
        }
//        set = createSet(sinEntries);
//        data.addDataSet(set);
//        mActivity.lineChart.setData(data);
        sinSet = new LineDataSet(sinEntries, "sin curve");
        sinSet.setColor(Color.BLUE);
        sinSet.setCircleColor(Color.BLUE);
        dataSets.add(sinSet);
        LineData data;
        data = new LineData(dataSets);
        mActivity.lineChart.setData(data);
        mActivity.lineChart.notifyDataSetChanged();
        mActivity.lineChart.invalidate();
//        mActivity.lineChart.moveViewToX(sinSet.getEntryCount());

        sinfun();
  ;

        mActivity.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.updategraph = true;
                sinfun();
            }
        });




    }



    private void sinfun() {
        LineData data = mActivity.lineChart.getLineData();
        if(data != null)
        {


            while(true) {

                data.addEntry(new Entry(i, (float) Math.sin(i) + 5), 0);
//                sinEntries.add(new Entry(i ,(float) Math.sin(i) + 5));
//                Entry sinv = sinEntries.get((int) (i-7f));
//                sinEntries.remove(sinv);
                if(!mActivity.updategraph)
                {
                    break;
                }
                float xmin = data.getXMin();
                dataSets.remove(sinSet.removeEntryByXValue(xmin));
                data.notifyDataChanged();
                mActivity.lineChart.notifyDataSetChanged();
                mActivity.lineChart.setVisibleXRangeMaximum(150);
                mActivity.lineChart.moveViewToX(data.getEntryCount());
                i += 0.02f;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        else{
            Toast.makeText(mActivity, "Sin entries null!", Toast.LENGTH_SHORT).show();
        }
//        while(true){
//            sinEntries.add(0, new Entry(i, (float) Math.sin(i) + 5));
//            i += 0.02f;
//        }
    }
}
