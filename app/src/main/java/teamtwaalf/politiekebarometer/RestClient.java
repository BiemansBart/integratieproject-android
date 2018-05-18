package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


import java.io.Console;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtwaalf.politiekebarometer.model.Graph;

import static java.util.Collections.addAll;


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
                List<Graph> grafieken = response.body();
                     result.addAll(grafieken);


            }
            @Override
            public void onFailure(Call<List<Graph>> call, Throwable t) {
                Log.d("LOGKEY", "Heel hard gefaald");
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return result;
    }





}
