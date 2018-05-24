package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
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
    private ImageButton imbAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        //butterknife aanroepen
        ButterKnife.bind(this);
        imbAlert = findViewById(R.id.imbAlert);
        imbAlert.setActivated(true);
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

    @OnClick(R.id.imbAlert)
    public void ShowAlerts(){
        System.out.println("show alerts");
        Intent intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }

}
