package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;
import teamtwaalf.politiekebarometer.adapter.GraphAdapter;
import teamtwaalf.politiekebarometer.model.Graph;
import teamtwaalf.politiekebarometer.model.GraphList;
import teamtwaalf.politiekebarometer.model.GraphType;

public class GraphActivity extends Activity {
private Random random = new Random();
//annotatie boven views zetten --> rebuilden
private ListView lvGraphs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //butterknife aanroepen
        lvGraphs = findViewById(R.id.lvGraphs);

        //rxjava-->
        GraphAdapter graphAdapter = new GraphAdapter(this,GraphList.graphList);
        lvGraphs.setAdapter(graphAdapter);

    }

}
