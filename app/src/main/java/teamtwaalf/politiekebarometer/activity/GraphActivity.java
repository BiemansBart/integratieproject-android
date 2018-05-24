package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import teamtwaalf.politiekebarometer.R;
import teamtwaalf.politiekebarometer.RestClient;
import teamtwaalf.politiekebarometer.adapter.GraphAdapter;
import teamtwaalf.politiekebarometer.model.Graph;

public class GraphActivity extends Activity {
    private Random random = new Random();
    //annotatie boven views zetten --> rebuilden
    private ListView lvGraphs;
    private RestClient restClient;
    private List<Graph> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //butterknife aanroepen
        Log.d("LOGKEY", "IETS LOGGEN PLS");
         restClient = new RestClient(this);
        try {
            System.out.println("IN TRY");
            Intent intent = getIntent();
            restClient.GetGrafieken(intent.getStringExtra("userId"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getGraphs(List<Graph> graphs) {
        System.out.println("GRAPHLENGTH: " + graphs.size() );

        System.out.println("DIT WERKT DUS WEL! Grootte van result :" + result.size());

        System.out.println("VLAK VOOR AANMAKEN: " + result.size());
        lvGraphs = findViewById(R.id.lvGraphs);
        GraphAdapter adapter = new GraphAdapter(this,new ArrayList<>(graphs));
        lvGraphs.setAdapter(adapter);
    }

    public void getUserGraphs(){

    }


}
