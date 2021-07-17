package com.example.thrededgraph;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class MainActivity extends AppCompatActivity{

    LineGraphSeries<DataPoint> series;
    public GraphThread mgraphthread;
    private TextView y;
    private Button enter;
    public Button play, pause;
    private EditText sinfun;
    GraphView graphView;
    boolean updategraph = true;
    LineChart lineChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initviews();

        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.setDrawGridBackground(false);

        lineChart.setPinchZoom(true);

        lineChart.setBackgroundColor(Color.WHITE);

        series = new LineGraphSeries<>();
        Legend l = lineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextColor(Color.WHITE);

        XAxis xl = lineChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(true);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
//        leftAxis.setAxisMaximum(5f);
//        leftAxis.setAxisMinimum(5f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);


        lineChart.getAxisLeft().setDrawGridLines(true);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.setDrawBorders(true);


        mgraphthread = new GraphThread(MainActivity.this);
        mgraphthread.setName("sin graph");
        mgraphthread.start();

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startthread();
            }
        });




        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(updategraph == false) {
                    updategraph = true;
//                    mgraphthread.start();
//                    String sin = sinfun.getText().toString();
//                    Message message = Message.obtain();
//                    message.obj = sin;
//                    mgraphthread.mhandler.sendMessage(message);
//                    mgraphthread.mhandler.thread.start();
                }
                else {
                    Toast.makeText(MainActivity.this, "Already running!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GraphHandler handler = new GraphHandler(MainActivity.this);
                GraphThread thread = new GraphThread(MainActivity.this);
                if(mgraphthread != null){
                    updategraph = false;
//                    handler.dataSets.clear();
                    Message message = Message.obtain();
//                    mgraphthread.mhandler.removeCallbacksAndMessages(null);
//                    mgraphthread.mhandler.thread.interrupt();
//                    lineChart.invalidate();
//                    lineChart.clear();
                }else{
                    Toast.makeText(MainActivity.this, "Already stopped!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void startthread() {
        String sin = sinfun.getText().toString();
        if (sin.equals("sin(t)")) {
            Message message = Message.obtain();
            message.obj = sin;
            mgraphthread.mhandler.sendMessage(message);
        }
        else{
            Toast.makeText(MainActivity.this, "Please enter sin fun!", Toast.LENGTH_SHORT).show();
        }
    }


    private void initviews() {
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        lineChart = findViewById(R.id.graph);
        y = findViewById(R.id.y);
        enter = findViewById(R.id.enter);
        sinfun = findViewById(R.id.sin);
    }
}