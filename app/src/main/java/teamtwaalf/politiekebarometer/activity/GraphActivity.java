package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;
import teamtwaalf.politiekebarometer.model.Graph;

public class GraphActivity extends Activity {
private Random random = new Random();
//annotatie boven views zetten --> rebuilden
private ListView lvGraphs;
private RestClient restClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //butterknife aanroepen
        lvGraphs = findViewById(R.id.lvGraphs);
        restClient = new RestClient(this);
            List<Graph> grafieken = RestClient.result;
            for (Graph graph : grafieken) {
                Log.d("LOGKEY",graph.getSubject() + "JAAAA");
            }


        //rxjava-->
      /*  GraphAdapter graphAdapter = new GraphAdapter(this,GraphList.graphList);
        lvGraphs.setAdapter(graphAdapter);*/

    }

}
