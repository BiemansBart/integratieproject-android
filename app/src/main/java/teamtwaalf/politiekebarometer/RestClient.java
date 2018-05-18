package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


import java.io.Console;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtwaalf.politiekebarometer.activity.GraphActivity;
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


    public List<Graph> getResult() throws IOException {

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GraphApi.BASE_URL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GraphApi api = retrofit.create(GraphApi.class);
        Call<List<Graph>> call = api.TestDataGrafieken();

        call.enqueue(new Callback<List<Graph>>() {
            @Override
            public void onResponse(Call<List<Graph>> call, Response<List<Graph>> response) {
                if (response.isSuccessful()) {
                    ((GraphActivity) context).printData(response.body());
                } else {
                    Toast.makeText(context, "Er is een fout opgetreden met het inladen van de grafieken, gelieve het opnieuw te proberen.s", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Graph>> call, Throwable t) {
                Toast.makeText(context, "Er is iets fout gegaan met het inlezen van de data, gelieve het opnieuw te proberen.", Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }


}
