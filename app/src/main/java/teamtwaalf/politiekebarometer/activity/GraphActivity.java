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
import teamtwaalf.politiekebarometer.adapter.AlertAdapter;
import teamtwaalf.politiekebarometer.adapter.GraphAdapter;
import teamtwaalf.politiekebarometer.model.AlertMessage;
import teamtwaalf.politiekebarometer.model.Graph;

public class GraphActivity extends Activity {
    private Random random = new Random();

    //annotatie boven views zetten --> rebuilden
    private ListView lvGraphs;
    private RestClient restClient;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        ButterKnife.bind(this);
        //butterknife aanroepen
        restClient = new RestClient(this);
        try {
            Intent intent = getIntent();
            id = intent.getStringExtra("userId");
            System.out.println("ID VOOR CRASH : " + id);
            restClient.GetGrafieken(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getGraphs(List<Graph> graphs) {
        System.out.println("GRAPHLENGTH: " + graphs.size());
        lvGraphs = findViewById(R.id.lvGraphs);
        GraphAdapter adapter = new GraphAdapter(this, new ArrayList<>(graphs));
        lvGraphs.setAdapter(adapter);
    }

    @OnClick(R.id.imbAlert)
    public void ShowAlerts() {
        Intent intent = new Intent(GraphActivity.this, AlertActivity.class);
        intent.putExtra("userId",id);
        startActivity(intent);
    }

    @OnClick(R.id.imageButtonLogOut)
    public void LogOut() {
        Intent intent = new Intent(GraphActivity.this, MainActivity.class);
        startActivity(intent);
    }

}
