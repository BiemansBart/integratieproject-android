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

import butterknife.ButterKnife;
import butterknife.OnClick;
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
        ButterKnife.bind(this);
        //butterknife aanroepen
         restClient = new RestClient(this);
        try {
            Intent intent = getIntent();
            restClient.getUserAlerts(intent.getStringExtra("userId"));
            restClient.GetGrafieken(intent.getStringExtra("userId"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getGraphs(List<Graph> graphs) {
        System.out.println("GRAPHLENGTH: " + graphs.size() );
        lvGraphs = findViewById(R.id.lvGraphs);
        GraphAdapter adapter = new GraphAdapter(this,new ArrayList<>(graphs));
        lvGraphs.setAdapter(adapter);
    }

    @OnClick(R.id.imageButton2)
    public void ShowAlerts(){
        System.out.println("knop werkt!");
    }
    @OnClick(R.id.imageButtonLogOut)
    public void LogOut(){
        Intent intent = new Intent(GraphActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
