package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtwaalf.politiekebarometer.activity.AlertActivity;
import teamtwaalf.politiekebarometer.activity.GraphActivity;
import teamtwaalf.politiekebarometer.activity.MainActivity;
import teamtwaalf.politiekebarometer.model.AlertMessage;
import teamtwaalf.politiekebarometer.model.Graph;

public class RestClient {
    //  api/user parametiseren
    //Retrofit of OKhttp
    private Context context;
    private Random random = new Random();
    public List<Graph> result = new ArrayList<>();

    public RestClient(Context c) {
        this.context = c;
    }


    public List<Graph> GetGrafieken(String userId) throws IOException {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GraphApi.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GraphApi api = retrofit.create(GraphApi.class);

        // halt de dubbele quotes uit de url omdat die anders niet werkt.
        String encodedData = userId.replace("\"", "");
        Call<List<Graph>> call = api.grafiekenPerUser(encodedData);

        call.enqueue(new Callback<List<Graph>>() {
            @Override
            public void onResponse(Call<List<Graph>> call, Response<List<Graph>> response) {
                if (response.isSuccessful()) {
                    ((GraphActivity) context).getGraphs(response.body());
                } else {
                    Toast.makeText(context, "Er is een fout opgetreden met het inladen van de grafieken, gelieve het opnieuw te proberen.s", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Graph>> call, Throwable t) {
                Toast.makeText(context, "Er is iets fout gelopen. Error boodschap : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return result;
    }

    public void getUserId(String email, String password) {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GraphApi.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GraphApi api = retrofit.create(GraphApi.class);
        Call<String> call = api.getUserId(email, password);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String id = response.body();
                    ((MainActivity) context).LogGebruikerIn(id);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(context, "Er is een fout opgetreden, probeer het later nog eens. Error boodschap : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUserAlerts(String id){
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GraphApi.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GraphApi api = retrofit.create(GraphApi.class);
        // halt de dubbele quotes uit de url omdat die anders niet werkt.
        String encodedData = id.replace("\"", "");
        Call<List<AlertMessage>> call = api.getUserAlerts(encodedData);

        call.enqueue(new Callback<List<AlertMessage>>() {
            @Override
            public void onResponse(Call<List<AlertMessage>> call, Response<List<AlertMessage>> response) {
                if(response.isSuccessful()){
                    System.out.println("OnResponse : " + response.body().size());
                    ((AlertActivity)context).showUserAlerts(response.body());
                }else{
                    System.out.println("Groote : " + response.body().size() );
                    Toast.makeText(context, "Er is iets mis gegaan bij het ophalen van alerts", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<AlertMessage>> call, Throwable t) {
                System.out.println("error : " + t.getMessage() );

                Toast.makeText(context, "Er is iets mis gegaan bij het ophalen van alerts. Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
