package teamtwaalf.politiekebarometer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import teamtwaalf.politiekebarometer.activity.MainActivity;
import teamtwaalf.politiekebarometer.model.Graph;
import teamtwaalf.politiekebarometer.model.GraphList;
import teamtwaalf.politiekebarometer.model.GraphType;

public class RestClient {
    //  api/user parametiseren
    //Retrofit of OKhttp
    private Context context;
    private Random random = new Random();

    public RestClient(Context c) {
        this.context = c;
    }


    public String getResult() throws IOException {
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://10.134.216.25:8012/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        GraphApi api = retrofit.create(GraphApi.class);
        Call<List<Graph>> call = api.grafiekenPerUser("1"); // Geef hier de user id aan mee

        call.enqueue(new Callback<List<Graph>>() {
            @Override
            public void onResponse(Call<List<Graph>> call, Response<List<Graph>> response) {
                List<Graph> grafieken = response.body();
                // kies hier wat je doet met deze lijst
            }

            @Override
            public void onFailure(Call<List<Graph>> call, Throwable t) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    return  null;
    }

    public void InitialiseGraphList() {
        String title = "";
        for (int i = 0; i <10; i++) {
            //Initialise title
            StringBuilder builder = new StringBuilder();
            builder.append("Grafiek ");
            builder.append(String.valueOf(i));
            title = builder.toString();


            //Initialise labels
            List<String> labels = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                labels.add(String.valueOf((j)));
            }

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

}
