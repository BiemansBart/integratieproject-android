package teamtwaalf.politiekebarometer.activity;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtwaalf.politiekebarometer.GraphApi;
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
            result.addAll(restClient.getResult());
        } catch (IOException e) {
            e.printStackTrace();
        }

        lvGraphs = findViewById(R.id.lvGraphs);
        System.out.println("");
        GraphAdapter adapter = new GraphAdapter(this,new ArrayList<>(result));
        lvGraphs.setAdapter(adapter);
    }

    public void getGraphs(List<Graph> graphs) {
        System.out.println("GRAPHLENGTH: " + graphs.size());
    }


}
