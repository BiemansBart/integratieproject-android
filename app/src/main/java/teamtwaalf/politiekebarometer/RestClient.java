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


<<<<<<< HEAD
=======
    public void InitialiseGraphList() {
        String title = "";
        for (int i = 0; i <10; i++) {
            //Initialise title
            StringBuilder builder = new StringBuilder();
            builder.append("Grafiek ");
            builder.append(String.valueOf(i));
            title = builder.toString();




            //Initialise subjects
            String firstSubject = "Bart De Wever";
            String secondSubject = "Geert Bourgeois";
            String thirdSubject = "Imade Annouri";
            String fourthSubject = "Theo Francken";
            String fifthSubject = "Bart Somers";

            //Initialise data
            List<String> dataFirstSubject = new ArrayList<>();
            List<String> dataSecondSubject = new ArrayList<>();
            List<String> dataThirdSubject = new ArrayList<>();
            List<String> dataFourthSubject = new ArrayList<>();
            List<String> dataFifthSubject = new ArrayList<>();
            for (int j = 0; j < labels.size(); j++) {
                dataFirstSubject.add(String.valueOf(random.nextInt(100)));
                dataSecondSubject.add(String.valueOf(random.nextInt(100)));
                dataThirdSubject.add(String.valueOf(random.nextInt(100)));
                dataFourthSubject.add(String.valueOf(random.nextInt(100)));
                dataFifthSubject.add(String.valueOf(random.nextInt(100)));
            }

            Graph g = new Graph(1,
                    GraphType.line,
                    labels, dataFirstSubject,
                    dataSecondSubject,
                    dataThirdSubject,
                    dataFourthSubject,
                    dataFifthSubject,
                    title,
                    firstSubject,
                    secondSubject,
                    thirdSubject,
                    fourthSubject,
                    fifthSubject
            );

            //Set random graphtype
            if(random.nextInt(100)>50){
                g.setType(GraphType.line);
                System.out.println("LINE");
            }else {
                g.setType(GraphType.pie);
                System.out.println("BAR");
            }



            GraphList.graphList.add(g);
        }

    }


>>>>>>> 80c0ca6325ac981632cb23a6ec09e150012f88cb
}
